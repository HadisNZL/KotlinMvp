package com.twobbble.biz.assist

import android.content.Context
import com.hadis.ktbyhadis.application.App
import com.twobbble.tools.Utils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by niuzilin on 2017/08/15.
 */
class RetrofitFactory private constructor() {
//    val API_BASE_URL = "https://api.dribbble.com/v1/"
    val API_BASE_URL = "http://gank.io/api/"
    val WEBSITE_BASE_URL = "https://dribbble.com/"
    val TIMEOUT: Long = 5
    private var mRetrofit: Retrofit? = null
    private var mNetService: NetService? = null

    init {
        if (mRetrofit == null) createRetrofit(App.instance)
    }

    companion object {
        fun getInstance(): RetrofitFactory {
            return Single.Instance
        }
    }

    private object Single {
        val Instance = RetrofitFactory()
    }

    /**
     * 配置OkHttpClient、Retrofit、NetService三个关键对象
     * @param context
     */
    fun createRetrofit(context: Context) {
        mRetrofit = Retrofit.Builder().client(constructClient(context)).baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
        mNetService = mRetrofit?.create(NetService::class.java)
    }

    fun createWebsiteRetrofit(): NetService {
        return Retrofit.Builder().client(constructClient(App.instance)).baseUrl(WEBSITE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
                .create(NetService::class.java)
    }

    /**
     * 构造OkHttpClient

     * @param context
     * *
     * @return
     */
    private fun constructClient(context: Context): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024
        val file = context.externalCacheDir
        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(Cache(file, cacheSize))
                .addNetworkInterceptor(getNetworkInterceptor())
                .addInterceptor(getInterceptor())//添加拦截器
                .retryOnConnectionFailure(true)
                .build()

        return client
    }

    /**
     * 设置返回数据的  Interceptor  判断网络   没网读取缓存
     */
    fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!Utils.isNetworkAvailable(App.instance)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            chain.proceed(request)
        }
    }

    fun getNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if (Utils.isNetworkAvailable(App.instance)) {
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            } else {
                // 无网络时，设置超时为4周
                val maxStale: Long = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build()
            }
            response
        }
    }

    /**
     * 将构造好的service对象返回出去

     * @return NetService
     */
    fun getService(): NetService = mNetService!!
}
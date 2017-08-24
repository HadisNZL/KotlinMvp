package com.twobbble.biz.assist

import com.hadis.ktbyhadis.entity.AndMol
import com.hadis.ktbyhadis.entity.VideoMol
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.wingsofts.gankclient.bean.JsonResult
import io.reactivex.Flowable
import org.jetbrains.annotations.NotNull
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by niuzilin on 2017/8/11.
 */
interface NetService {
    /**
     * 获取一个android列表
     *
     */

    @GET("data/{type}/{page}/{pagesize}")
    fun getAndroid(@NotNull @Path("type") type: String,
                   @Path("page") page: Int?,
                   @Path("pagesize") pagesize: Int?): Flowable<JsonResult<MutableList<AndMol>>>

    /**
     * 视频列表
     */
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getVideoList(): Flowable<MutableList<VideoMol>>

    /**
     * 视频列表更多
     */
    @GET("v3/videos")
    fun getVideoMoreList(@Query("categoryName") categoryName: String, @Query("strategy") strategy: String,
                         @Query("udid") udid: String, @Query("vc") vc: Int): Flowable<VideoMoreMol>

    /**
     * 视频列表更多加载更多
     */
    @GET("v3/videos")
    fun getVideoMoreList1(@Query("start") start: Int, @Query("num") num: Int,
                          @Query("categoryName") categoryName: String, @Query("strategy") strategy: String): Flowable<VideoMoreMol>
}
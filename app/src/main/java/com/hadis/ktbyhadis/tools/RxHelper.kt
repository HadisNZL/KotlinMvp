package com.twobbble.tools

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by niuzilin on 2017/8/12.
 */
class RxHelper {
    companion object {
        /**
         * 输入输出都为List的observable模板
         *
         * @param subscribeThread 订阅的线程
         * @param unSubscribeThread 解除订阅的线程
         * @param observeThread 结果返回的线程
         */
        fun <T> listModeThread(subscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                               unSubscribeThread: io.reactivex.Scheduler = Schedulers.io(),
                               observeThread: io.reactivex.Scheduler? = AndroidSchedulers.mainThread()): FlowableTransformer<MutableList<T>,
                MutableList<T>> {
            return FlowableTransformer { observable ->
                observable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread!!).
                        unsubscribeOn(unSubscribeThread).
                        observeOn(observeThread)
            }
        }

        /**
         * 输入输出都为单个对象的observable模板
         *
         * @param subscribeThread 订阅的线程
         * @param unSubscribeThread 解除订阅的线程
         * @param observeThread 结果返回的线程
         * FlowableTransformer  这里对应Api的Flowable
         *
         */
        fun <T> singleModeThread(subscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                 unSubscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                 observeThread: io.reactivex.Scheduler? = AndroidSchedulers.mainThread()): FlowableTransformer<T, T> {
            return FlowableTransformer { observable ->
                observable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread!!).
                        unsubscribeOn(unSubscribeThread).
                        observeOn(observeThread)
            }
        }

        /**
         * 输入输出都为单个对象的observable模板
         *
         * @param subscribeThread 订阅的线程
         * @param unSubscribeThread 解除订阅的线程
         * @param observeThread 结果返回的线程
         */
        fun <T> singleModeThreadNormal(subscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                       unSubscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                       observeThread: io.reactivex.Scheduler? = AndroidSchedulers.mainThread()): FlowableTransformer<T, T> {
            return FlowableTransformer { observable ->
                observable.subscribeOn(subscribeThread!!).
                        unsubscribeOn(unSubscribeThread).
                        observeOn(observeThread)
            }
        }
    }
}
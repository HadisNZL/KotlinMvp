package com.twobbble.tools

import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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
        fun <T> listModeThread(subscribeThread: Scheduler? = Schedulers.io(),
                               unSubscribeThread: Scheduler? = Schedulers.io(),
                               observeThread: Scheduler? = AndroidSchedulers.mainThread()): Observable.Transformer<MutableList<T>, MutableList<T>> {
            return Observable.Transformer { observable ->
                observable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread).
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
        fun <T> singleModeThread(subscribeThread: Scheduler? = Schedulers.io(),
                                 unSubscribeThread: Scheduler? = Schedulers.io(),
                                 observeThread: Scheduler? = AndroidSchedulers.mainThread()): Observable.Transformer<T, T> {
            return Observable.Transformer { observable ->
                observable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread).
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
        fun <T> singleModeThreadNormal(subscribeThread: Scheduler? = Schedulers.io(),
                                       unSubscribeThread: Scheduler? = Schedulers.io(),
                                       observeThread: Scheduler? = AndroidSchedulers.mainThread()): Observable.Transformer<T, T> {
            return Observable.Transformer { observable ->
                observable.subscribeOn(subscribeThread).
                        unsubscribeOn(unSubscribeThread).
                        observeOn(observeThread)
            }
        }
    }
}
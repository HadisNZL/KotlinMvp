package com.twobbble.presenter

import io.reactivex.disposables.CompositeDisposable


/**
 * Created by niuzilin on 2017/8/11.
 */
abstract class BasePresenter {
    val mSubscription: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    open fun unSubscriber() {
        if (mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }
}
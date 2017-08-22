package com.twobbble.presenter

import rx.subscriptions.CompositeSubscription

/**
 * Created by niuzilin on 2017/8/11.
 */
abstract class BasePresenter {
    val mSubscription: CompositeSubscription by lazy {
        CompositeSubscription()
    }


    open fun unSubscriber() {
        if (mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe()
        }
    }
}
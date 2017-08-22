package com.twobbble.tools

import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.twobbble.view.api.IBaseView
import rx.Subscriber

/**
 * Created by niuzilin on 2017/8/11.
 */
abstract class NetSubscriber<T>(val baseView: IBaseView? = null) : Subscriber<T>() {
    override fun onStart() {
        super.onStart()
        if (!Utils.isNetworkAvailable(App.instance)) {
            baseView?.hideProgress()
            onFailed(App.instance.resources.getString(R.string.net_disable))
            unsubscribe()
        } else {
            baseView?.showProgress()
        }
    }

    override fun onCompleted() {
        baseView?.hideProgress()
    }

    override fun onError(t: Throwable) {
        baseView?.hideProgress()
        t.printStackTrace()
        onFailed(t.message.toString())
    }

    abstract fun onFailed(msg: String)
}
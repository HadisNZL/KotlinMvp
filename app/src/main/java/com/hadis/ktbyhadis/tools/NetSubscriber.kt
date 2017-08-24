package com.twobbble.tools

import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.twobbble.view.api.IBaseView
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by niuzilin on 2017/8/11.
 */
abstract class NetSubscriber<T>(val baseView: IBaseView? = null) : ResourceSubscriber<T>() {
    override fun onStart() {
        super.onStart()
        if (!Utils.isNetworkAvailable(App.instance)) {
            baseView?.hideProgress()
            onFailed(App.instance.resources.getString(R.string.net_disable))
            dispose()
        } else {
            baseView?.showProgress()
        }
    }

    override fun onComplete() {
        baseView?.hideProgress()
    }

    override fun onError(t: Throwable) {
        baseView?.hideProgress()
        t.printStackTrace()
        onFailed(t.message.toString())
    }

    abstract fun onFailed(msg: String)
}
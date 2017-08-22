package com.hadis.ktbyhadis.application

import android.app.Application
import com.twobbble.tools.delegates.NotNullSingleValueVar

/**
 * Created by niuzilin on 2017/8/11.
 */
class App : Application() {
    companion object {
        var instance: App by NotNullSingleValueVar.DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
    }
}
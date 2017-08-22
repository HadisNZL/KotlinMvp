package com.twobbble.biz.impl

import com.twobbble.biz.assist.NetService
import com.twobbble.biz.assist.RetrofitFactory

/**
 * Created by niuzilin on 2017/8/11.
 */

open class BaseBiz {
    fun getNetService(): NetService = RetrofitFactory.getInstance().getService()
}
package com.twobbble.biz.impl

import com.twobbble.biz.assist.NetService
import com.twobbble.biz.assist.RetrofitFactory1


/**
 * Created by niuzilin on 2017/8/11.
 */
open class BaseBiz1 {
    fun getNetService(): NetService = RetrofitFactory1.getInstance().getService()
}
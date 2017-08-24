package com.hadis.ktbyhadis.biz.api

import com.hadis.ktbyhadis.entity.AndMol
import com.twobbble.tools.NetSubscriber
import com.wingsofts.gankclient.bean.JsonResult
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/11.
 */
interface IAndMolBiz {
    /**
     *获取列表
     */
    fun getAndroid(@NotNull type: String, page: Int?, pagesize: Int?,
                   subscriber: NetSubscriber<JsonResult<MutableList<AndMol>>>): Disposable
}
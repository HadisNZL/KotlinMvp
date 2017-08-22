package com.hadis.ktbyhadis.view.api

import com.hadis.ktbyhadis.entity.AndMol
import com.twobbble.view.api.IBaseView
import com.wingsofts.gankclient.bean.JsonResult
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/15.
 */
interface IAndMolView : IBaseView {

    /**
     * 获取android内容成功
     * @param ands 列表
     */
    fun getDataSucess(ands: JsonResult<MutableList<AndMol>>)

    /**
     * 获取android内容失败
     * @param msg 失败的原因
     */

    fun getDataFailed(@NotNull msg: String)
}
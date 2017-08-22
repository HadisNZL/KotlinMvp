package com.hadis.ktbyhadis.view.api

import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.twobbble.view.api.IBaseView
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/15.
 */
interface IVideoMoreMolView : IBaseView {

    /**
     * 获取video列表成功
     * @param ands 列表
     */
    fun getDataSucess(ands: VideoMoreMol)

    /**
     * 获取内容失败
     * @param msg 失败的原因
     */

    fun getDataFailed(@NotNull msg: String)
}
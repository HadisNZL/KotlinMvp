package com.hadis.ktbyhadis.view.api

import com.hadis.ktbyhadis.entity.VideoMol
import com.twobbble.view.api.IBaseView
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/15.
 */
interface IVideoMolView : IBaseView {

    /**
     * 获取video列表成功
     * @param ands 列表
     */
    fun getDataSucess(ands: MutableList<VideoMol>)

    /**
     * 获取内容失败
     * @param msg 失败的原因
     */

    fun getDataFailed(@NotNull msg: String)
}
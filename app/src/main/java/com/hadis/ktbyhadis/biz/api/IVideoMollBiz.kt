package com.hadis.ktbyhadis.biz.api

import com.hadis.ktbyhadis.entity.VideoMol
import com.twobbble.tools.NetSubscriber
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/11.
 */
interface IVideoMollBiz {
    /**
     *获取视频列表
     */
    fun getVideoList(@NotNull subscriber: NetSubscriber<MutableList<VideoMol>>): Disposable
}
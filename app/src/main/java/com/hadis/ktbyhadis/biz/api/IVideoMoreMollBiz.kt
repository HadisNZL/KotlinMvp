package com.hadis.ktbyhadis.biz.api

import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.twobbble.tools.NetSubscriber
import io.reactivex.disposables.Disposable

/**
 * Created by niuzlin on 2017/8/15.
 */
interface IVideoMoreMollBiz {
    /**
     *获取视频列表
     */
    fun getVideoMoreList(categoryName: String, strategy: String, udid: String, vc: Int,
                         subscriber: NetSubscriber<VideoMoreMol>): Disposable

    fun getVideoMoreList1( start :Int, num :Int, categoryName :String, strategy :String,
                         subscriber: NetSubscriber<VideoMoreMol>): Disposable
}
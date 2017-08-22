package com.hadis.ktbyhadis.biz.impl

import com.hadis.ktbyhadis.biz.api.IVideoMollBiz
import com.hadis.ktbyhadis.entity.VideoMol
import com.twobbble.biz.impl.BaseBiz1
import com.twobbble.tools.NetSubscriber
import com.twobbble.tools.RxHelper
import rx.Subscription

/**
 * Created by niuzilin on 2017/8/11.
 */
class VideoMolBiz : IVideoMollBiz, BaseBiz1() {
    override fun getVideoList(subscriber: NetSubscriber<MutableList<VideoMol>>): Subscription {
        getNetService().getVideoList().compose(RxHelper.listModeThread()).subscribe(subscriber)
        return subscriber
    }
}
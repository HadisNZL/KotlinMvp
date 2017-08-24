package com.hadis.ktbyhadis.biz.impl

import com.hadis.ktbyhadis.biz.api.IVideoMoreMollBiz
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.twobbble.biz.impl.BaseBiz1
import com.twobbble.tools.NetSubscriber
import com.twobbble.tools.RxHelper
import io.reactivex.disposables.Disposable

/**
 * Created by niuzilin on 2017/8/11.
 */
class VideoMoreMolBiz : IVideoMoreMollBiz, BaseBiz1() {
    override fun getVideoMoreList1(start: Int, num: Int, categoryName: String, strategy: String, subscriber: NetSubscriber<VideoMoreMol>): Disposable {
        getNetService().getVideoMoreList1(start, num, categoryName, strategy).compose(RxHelper
                .singleModeThread()).subscribe(subscriber)
        return subscriber
    }

    override fun getVideoMoreList(categoryName: String, strategy: String, udid: String, vc: Int,
                                  subscriber: NetSubscriber<VideoMoreMol>): Disposable {
        getNetService().getVideoMoreList(categoryName, strategy, udid, vc).compose(RxHelper
                .singleModeThread()).subscribe(subscriber)
        return subscriber
    }
}
package com.hadis.ktbyhadis.presenter

import com.hadis.ktbyhadis.biz.api.IVideoMollBiz
import com.hadis.ktbyhadis.biz.impl.VideoMolBiz
import com.hadis.ktbyhadis.entity.VideoMol
import com.hadis.ktbyhadis.view.api.IVideoMolView
import com.twobbble.presenter.BasePresenter
import com.twobbble.tools.NetSubscriber

/**
 * Created by niuzilin on 2017/8/11.
 */

class IVideoMolPresenter(val mVideoMolView: IVideoMolView) : BasePresenter() {

    private val mVideoMolBiz: IVideoMollBiz by lazy {
        VideoMolBiz()
    }

    fun getVideoList() {
        val subscriber = mVideoMolBiz.getVideoList(object
            : NetSubscriber<MutableList<VideoMol>>(mVideoMolView) {
            override fun onNext(t: MutableList<VideoMol>?) {
                mVideoMolView.getDataSucess(t!!)
            }

            override fun onFailed(msg: String) {
                mVideoMolView.getDataFailed(msg)
            }
        })
        mSubscription.add(subscriber)
    }

}
package com.hadis.ktbyhadis.presenter

import com.hadis.ktbyhadis.biz.api.IVideoMoreMollBiz
import com.hadis.ktbyhadis.biz.impl.VideoMoreMolBiz
import com.hadis.ktbyhadis.view.api.IVideoMoreMolView
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.twobbble.presenter.BasePresenter
import com.twobbble.tools.NetSubscriber

/**
 * Created by niuzilin on 2017/8/11.
 */
class IVideoMoreMolPresenter(val mVideoMoreMolView: IVideoMoreMolView) : BasePresenter() {

    private val mVideoMoreMolBiz: IVideoMoreMollBiz by lazy {
        VideoMoreMolBiz()
    }

    fun getVideoMoreList(categoryName: String, strategy: String, udid: String, vc: Int) {
        val subscriber = mVideoMoreMolBiz.getVideoMoreList(categoryName, strategy, udid, vc, object
            : NetSubscriber<VideoMoreMol>(mVideoMoreMolView) {
            override fun onFailed(msg: String) {
                mVideoMoreMolView.getDataFailed(msg)
            }

            override fun onNext(t: VideoMoreMol?) {
                mVideoMoreMolView.getDataSucess(t!!)
            }
        })
        mSubscription.add(subscriber)
    }

    fun getVideoMoreList1(start: Int, num: Int, categoryName: String, strategy: String) {
        val subscriber = mVideoMoreMolBiz.getVideoMoreList1(start, num, categoryName, strategy, object
            : NetSubscriber<VideoMoreMol>(mVideoMoreMolView) {
            override fun onNext(t: VideoMoreMol?) {
                mVideoMoreMolView.getDataSucess(t!!)
            }

            override fun onFailed(msg: String) {
                mVideoMoreMolView.getDataFailed(msg)
            }
        })
        mSubscription.add(subscriber)
    }
}
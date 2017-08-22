package com.hadis.ktbyhadis.presenter

import com.hadis.ktbyhadis.biz.api.IAndMolBiz
import com.hadis.ktbyhadis.biz.impl.AndMolBiz
import com.hadis.ktbyhadis.entity.AndMol
import com.hadis.ktbyhadis.view.api.IAndMolView
import com.twobbble.presenter.BasePresenter
import com.twobbble.tools.NetSubscriber
import com.wingsofts.gankclient.bean.JsonResult
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/11.
 */

class IAndMolPresenter(val mIAndMolView: IAndMolView) : BasePresenter() {

    private val mAndMolBiz: IAndMolBiz by lazy {
        AndMolBiz()
    }

    fun getAndroid(@NotNull type: String, page: Int?, pagesize: Int?) {
        val subscriber = mAndMolBiz.getAndroid(type, page, pagesize, object
            : NetSubscriber<JsonResult<MutableList<AndMol>>>(mIAndMolView) {
            override fun onNext(t: JsonResult<MutableList<AndMol>>) {
                mIAndMolView.getDataSucess(t)
            }

            override fun onFailed(msg: String) {
                mIAndMolView.getDataFailed(msg)
            }
        })
        mSubscription.add(subscriber)
    }

}
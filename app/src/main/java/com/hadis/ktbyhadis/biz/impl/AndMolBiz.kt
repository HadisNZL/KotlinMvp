package com.hadis.ktbyhadis.biz.impl

import com.hadis.ktbyhadis.biz.api.IAndMolBiz
import com.hadis.ktbyhadis.entity.AndMol
import com.twobbble.biz.impl.BaseBiz
import com.twobbble.tools.NetSubscriber
import com.twobbble.tools.RxHelper
import com.wingsofts.gankclient.bean.JsonResult
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/11.
 */
class AndMolBiz : IAndMolBiz, BaseBiz() {
    override fun getAndroid(@NotNull type: String, page: Int?, pagesize: Int?, subscriber:
    NetSubscriber<JsonResult<MutableList<AndMol>>>): Disposable {
        getNetService().getAndroid(type, page, pagesize).compose(RxHelper.singleModeThread())
                .subscribe(subscriber)
        return subscriber
    }

}
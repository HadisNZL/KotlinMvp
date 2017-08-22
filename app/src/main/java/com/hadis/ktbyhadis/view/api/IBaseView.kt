package com.twobbble.view.api


/**
 * @dec 基类view
 * @by Niuzilin on 2017/08/15
 */
interface IBaseView {
    fun showProgress() {}

    fun hideProgress() {}

    fun showProgressDialog(msg: String? = null) {}

    fun hideProgressDialog() {}
}
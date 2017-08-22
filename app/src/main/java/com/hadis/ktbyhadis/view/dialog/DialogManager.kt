package com.twobbble.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.hadis.ktbyhadis.R
import com.twobbble.tools.toast
import kotlinx.android.synthetic.main.anim_begin_end.view.*
import kotlinx.android.synthetic.main.anim_drawable_dialog_layout.view.*
import kotlinx.android.synthetic.main.create_bucket_dialog.view.*

/**
 * @Des 对话框管理类
 * @author nzl
 * @time 2017.07.26
 */

class DialogManager(private val mContext: Context) {
    private var mDialog: Dialog? = null

    /**
     * 圆形进度条dialog
     * @param text
     */
    fun showCircleProgressDialog(text: String = mContext.resources.getString(R.string.please_wait)) {
        val dialog = ProgressDialog(mContext)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.isIndeterminate = true
        dialog.setMessage(text)
        dialog.show()
        mDialog = dialog
    }

    /**
     * 自定义帧动画Dialog
     */
    fun customAnimLoading(title: String = mContext.resources.getString(R.string.loading)) {
        val dialog = Dialog(mContext, R.style.MyDialog)
        dialog.window.setGravity(Gravity.LEFT and Gravity.RIGHT)

        dialog.window.attributes.x = 0
        dialog.window.attributes.y = 500

        val view = LayoutInflater.from(mContext).inflate(R.layout.anim_drawable_dialog_layout, null)
        view.id_tv_loadingmsg.text = title
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        mDialog = dialog
    }


    /**
     * 插值器
     * Android 动画之Interpolator插入器，比较简单和常用的：

    （1）LinearInterpolator：动画从开始到结束，变化率是线性变化。
    （2）AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
    （3）DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
    （4）CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
    （5）AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
     */

    private val animation: Animation
        get() {
            val mAnimation: Animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim)
            return mAnimation
        }

    fun rotateAnimLoading() {
        val dialog = Dialog(mContext, R.style.MyDialog)
        val view = LayoutInflater.from(mContext).inflate(R.layout.anim_begin_end, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)

        dialog.window.attributes.x = 0
        dialog.window.attributes.y = 500

        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        val animation: Animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim)
        animation.interpolator = LinearInterpolator()
        view.iv_bg.animation = animation
        view.iv_bg.startAnimation(animation)
        dialog.show()
        mDialog = dialog

    }


    fun showOptionDialog(title: String,
                         content: String,
                         CancelText: String = mContext.resources.getString(R.string.cancel),
                         confirmText: String = mContext.resources.getString(R.string.confirm),
                         onConfirm: () -> Unit,
                         onCancel: () -> Unit) {
        val dialog = AlertDialog.Builder(mContext)
        dialog.setTitle(title).setMessage(content).setPositiveButton(confirmText) { _, _ ->
            onConfirm()
        }.setNegativeButton(CancelText, { _, _ ->
            onCancel.invoke()
        }).setCancelable(false)

        mDialog = dialog.show()
    }

    fun showEditBucketDialog(name: String = "", description: String? = "", title: String? = mContext.resources.getString(R.string.create_bucket), onConfirm: (String, String?) -> Unit) {
        val dialog = AlertDialog.Builder(mContext)
        val view = LayoutInflater.from(mContext).inflate(R.layout.create_bucket_dialog, null)
        view.mBucketNameEdit.setText(name)
        view.mBucketDescriptionEdit.setText(description)
        dialog.setTitle(title).setPositiveButton(R.string.create) { _, _ ->
            if (!view.mBucketNameEdit.text.isNullOrBlank()) {
                onConfirm(view.mBucketNameEdit.text.toString(), view.mBucketDescriptionEdit.text.toString())
            } else {
                toast(R.string.bucket_name_null)
            }
        }.setNegativeButton(R.string.cancel, null).setView(view)
        mDialog = dialog.show()
    }

    fun dismissAll() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
            mDialog = null
            if (animation.hasStarted()) {
                animation.cancel()
            }
        }
    }

    val isShowing: Boolean
        get() = mDialog!!.isShowing
}

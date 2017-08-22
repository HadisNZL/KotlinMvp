package com.twobbble.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import org.jetbrains.anko.find
import org.jetbrains.annotations.NotNull

/**
 * Created by niuzilin on 2017/8/11.
 */

val View.ctx: Context
    get() = context

fun Activity.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, msg, time).show()
}

fun Activity.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT, actionMsg: String = "重试", action: (View) -> Unit) {
    Snackbar.make(view, msg, time).setAction(actionMsg, View.OnClickListener { action.invoke(view) }).show()
}

fun Fragment.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, msg, time).show()
}

fun Fragment.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT, actionMsg: String = "重试", action: (View) -> Unit) {
    Snackbar.make(view, msg, time).setAction(actionMsg, View.OnClickListener { action.invoke(view) }).show()
}

fun Any.log(msg: String) {
    Log.d(this.javaClass.simpleName, msg)
}

fun Any.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.instance, msg, length).show()
}

fun Any.toast(msg: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.instance, msg, length).show()
}

inline fun <reified T : Activity> Activity.newIntent(tag: String, string: String) {
    val intent = Intent(this, T::class.java)
    intent.putExtra(tag, string)
    startActivity(intent)
}

/**
 * 显示错误图片
 */
fun Fragment.showErrorImg(@NotNull viewGroup: ViewGroup, @NotNull msgResId: Int = R.string.no_shot, imgResID: Int = R.mipmap.img_empty_shots) {
    viewGroup.visibility = View.VISIBLE
    viewGroup.find<ImageView>(R.id.mErrorImg).setImageResource(imgResID)
    viewGroup.find<TextView>(R.id.mErrorText).setText(msgResId)
}

/**
 * 显示错误图片
 */
fun Fragment.showErrorImg(@NotNull viewGroup: ViewGroup, @NotNull msg: String, imgResID: Int = R.mipmap.img_empty_shots) {
    viewGroup.visibility = View.VISIBLE
    viewGroup.find<ImageView>(R.id.mErrorImg).setImageResource(imgResID)
    viewGroup.find<TextView>(R.id.mErrorText).text = msg
}

/**
 * 显示错误图片
 */
fun Activity.showErrorImg(@NotNull viewGroup: ViewGroup, @NotNull msgResId: Int = R.string.no_shot, imgResID: Int = R.mipmap.img_empty_shots) {
    viewGroup.visibility = View.VISIBLE
    viewGroup.find<ImageView>(R.id.mErrorImg).setImageResource(imgResID)
    viewGroup.find<TextView>(R.id.mErrorText).setText(msgResId)
}

/**
 * 显示错误图片
 */
fun Activity.showErrorImg(@NotNull viewGroup: ViewGroup, @NotNull msg: String, imgResID: Int = R.mipmap.img_empty_shots) {
    viewGroup.visibility = View.VISIBLE
    viewGroup.find<ImageView>(R.id.mErrorImg).setImageResource(imgResID)
    viewGroup.find<TextView>(R.id.mErrorText).text = msg
}

/**
 * 隐藏错无图片
 */
fun Fragment.hideErrorImg(@NotNull viewGroup: ViewGroup) {
    viewGroup.visibility = View.GONE
}

/**
 * 隐藏错无图片
 */
fun Activity.hideErrorImg(@NotNull viewGroup: ViewGroup) {
    viewGroup.visibility = View.GONE
}

//fun Fragment.startSpeak() {
//    //通过Intent传递语音识别的模式
//    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//    //语言模式和自由形式的语音识别
//    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//    //提示语音开始
//    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.start_speak))
//    //开始执行我们的Intent、语音识别
//    startActivityForResult(intent, Constant.VOICE_CODE)
//}
//
//fun Activity.startSpeak() {
//    //通过Intent传递语音识别的模式
//    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//    //语言模式和自由形式的语音识别
//    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//    //提示语音开始
//    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.start_speak))
//    //开始执行我们的Intent、语音识别
//    startActivityForResult(intent, Constant.VOICE_CODE)
//}
//
//inline fun Context.hasNavigationBar(block: () -> Unit) {
//    //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
//    val hasMenuKey = ViewConfiguration.get(this)
//            .hasPermanentMenuKey()
//    val hasBackKey = KeyCharacterMap
//            .deviceHasKey(KeyEvent.KEYCODE_BACK)
//
//    if (!hasMenuKey && !hasBackKey) {
//        block()
//    }
//}
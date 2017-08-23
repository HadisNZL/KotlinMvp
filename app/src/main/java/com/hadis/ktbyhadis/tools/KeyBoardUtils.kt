package com.hadis.ktbyhadis.tools

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @Des  键盘管理类
 * @author niuzilin
 * @time 2017/8/23
 */
object KeyBoardUtils {

    /**
     * 打开键盘获取焦点
     */
    fun openKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }


    /**
     * 关闭键盘
     */
    fun closeKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
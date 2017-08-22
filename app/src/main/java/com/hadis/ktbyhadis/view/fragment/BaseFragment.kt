package com.hadis.ktbyhadis.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hadis.ktbyhadis.application.App
import com.twobbble.view.dialog.DialogManager

/**
 * Created by niuzilin on 2017/8/15.
 */
abstract class BaseFragment : Fragment() {

    val mDialogMananer: DialogManager by lazy {
        DialogManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        mDialogMananer.dismissAll()
    }
}
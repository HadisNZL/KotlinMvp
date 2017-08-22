package com.hadis.ktbyhadis.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.hadis.ktbyhadis.R
import com.twobbble.view.dialog.DialogManager

/**
 * Created by niuzlin on 2017/8/11.
 */
open class BaseActivity : AppCompatActivity() {

    val mDialogMananer: DialogManager by lazy { DialogManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setupToolbar(toolbar: Toolbar) {
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.icon_back_bla)
        setSupportActionBar(toolbar)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDialogMananer.dismissAll()
    }
}
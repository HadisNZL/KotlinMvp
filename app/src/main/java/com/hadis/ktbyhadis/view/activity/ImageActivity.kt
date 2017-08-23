package com.hadis.ktbyhadis.view.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import kotlinx.android.synthetic.main.activity_image.*
import java.lang.Exception

@Suppress("UNREACHABLE_CODE")
class ImageActivity : BaseActivity() {

    var urlStr = ""
    var whoStr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        setupToolbar(toolbar)
        mDialogMananer.rotateAnimLoading()
        tv_title.text = intent.getStringExtra("who")
        toolbar.setNavigationIcon(R.drawable.icon_back_white)
        initView()
    }

    private fun initView() {
        urlStr = intent.getStringExtra("url")
        Glide.with(App.instance)
                .load(urlStr)
                .crossFade()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                        mDialogMananer.dismissAll()
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        mDialogMananer.dismissAll()
                        return false
                    }
                })
                .into(photo_view)

    }

}
package com.hadis.ktbyhadis.view.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import kotlinx.android.synthetic.main.activity_image.*
import uk.co.senab.photoview.PhotoView

@Suppress("UNREACHABLE_CODE")
class ImageActivity : BaseActivity() {

    var urlStr = ""
    var whoStr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        setupToolbar(toolbar)
        mDialogMananer.showCircleProgressDialog()
        tv_title.text = intent.getStringExtra("who")
        toolbar.setNavigationIcon(R.drawable.icon_back)
        initView()
    }

    private fun initView() {
        urlStr = intent.getStringExtra("url")
        Glide.with(App.instance).load(urlStr).crossFade().into(GlideDrawableImageViewTarget(photo_view) {
        })

    }

    private fun GlideDrawableImageViewTarget(photo_view: PhotoView?, any: Any): PhotoView? {
        mDialogMananer.dismissAll()
        return photo_view
    }
}
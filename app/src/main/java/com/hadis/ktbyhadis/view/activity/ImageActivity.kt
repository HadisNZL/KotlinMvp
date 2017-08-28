package com.hadis.ktbyhadis.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import kotlinx.android.synthetic.main.activity_image.*
import org.jetbrains.anko.toast
import zlc.season.rxdownload2.RxDownload
import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception

class ImageActivity : BaseActivity() {

    var urlStr = ""

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


        iv_down.setOnClickListener { downPic() }
    }


    private fun downPic() {

        val dir = Environment.getExternalStorageDirectory().toString() + "/ktByHadis"
        val name = System.currentTimeMillis().toString() + ".jpg"

        RxDownload.getInstance(App.instance).serviceDownload(urlStr, name, dir)
                .subscribe({
                    toast("开始下载")

                }, {
                    toast("下载失败")
                }, {
                    toast("已保存到SD卡KtByHadis文件夹下")
                })
        undateGallery(dir, name)//把文件插入到系统图库
    }

    /**
     * 把图片插入到系统图库
     */
    fun undateGallery(dir: String, name: String) {
        val file = File("$dir/$name")
        try {
            MediaStore.Images.Media.insertImage(App.instance.contentResolver, file.absolutePath, name, null)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        // 最后通知图库更新
        App.instance.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)))
    }
}
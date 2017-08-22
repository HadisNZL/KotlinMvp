package com.hadis.ktbyhadis.view.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hadis.ktbyhadis.R
import kotlinx.android.synthetic.main.activity_details.*
import android.content.Intent



class DetailsActivity : BaseActivity() {

    var urlStr = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupToolbar(toolbar)
        tv_title.text = "详情"
        initView()
    }

    private fun initView() {
        mDialogMananer.showCircleProgressDialog()
        urlStr = intent.getStringExtra("url")
        webView.loadUrl(urlStr)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mDialogMananer.dismissAll()
            }
        }

        share_iv.setOnClickListener { getShare() }
    }

    private fun getShare() {
        if (!urlStr.isEmpty()) {
            val textIntent = Intent(Intent.ACTION_SEND)
            textIntent.type = "text/plain"
            textIntent.putExtra(Intent.EXTRA_TEXT, urlStr)
            startActivity(Intent.createChooser(textIntent, "ktByHadis"))
        }
    }
}

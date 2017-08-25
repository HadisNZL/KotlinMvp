package com.hadis.ktbyhadis.view.activity

import android.Manifest
import android.graphics.Typeface
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.*
import com.hadis.ktbyhadis.R
import com.tbruyelle.rxpermissions2.RxPermissions
import com.twobbble.tools.newIntent
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        //设置全屏
        initView()
        //setAnimation()
    }

    private fun initView() {
        val fontType: Typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_name_english.typeface = fontType
        tv_english_intro.typeface = fontType
        initPermissionSet()
    }

    //base of rxJava2
    private fun initPermissionSet() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) {
                        setAnimation()
                    } else {
                        setAnimation()
                    }
                }

    }

    private fun setAnimation() {
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f)
        alphaAnimation.duration = 1500
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1500
        val rotateAnimation = RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1500
        val animationSet: AnimationSet = AnimationSet(true)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotateAnimation)
        animationSet.duration = 1500
        iv_icon_splash.startAnimation(animationSet)
        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                newIntent<Main2Activity>("Tag", "hehe")
                finish()
            }
        })


    }

}

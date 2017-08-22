package com.hadis.ktbyhadis.view.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.view.fragment.AboutFragment
import com.hadis.ktbyhadis.view.fragment.HomeFragment
import com.hadis.ktbyhadis.view.fragment.SecondFragment
import com.hadis.ktbyhadis.view.fragment.ThirdFragment
import com.twobbble.tools.toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity : BaseActivity(), View.OnClickListener {

    var homeFragment: HomeFragment? = null
    var secondFragment: SecondFragment? = null
    var thirdFragmnet: ThirdFragment? = null
    var aboutFragment: AboutFragment? = null
    var mExitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)
    }

    private fun initToolbar() {
        var today = getToday()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_mine.isChecked) {
                toast("设置暂未开放")
            } else {
                toast("搜索暂未开放")
//                searchFragment = SearchFragment()
//                searchFragment.show(fragmentManager, SEARCH_TAG)
            }
        }
    }

    private fun setRadioButton() {
        rb_home.isChecked = true
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is SecondFragment) {
                    secondFragment = item
                }
                if (item is ThirdFragment) {
                    thirdFragmnet = item
                }
                if (item is AboutFragment) {
                    aboutFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            secondFragment = SecondFragment()
            thirdFragmnet = ThirdFragment()
            aboutFragment = AboutFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, homeFragment)
            fragmentTrans.add(R.id.fl_content, secondFragment)
            fragmentTrans.add(R.id.fl_content, thirdFragmnet)
            fragmentTrans.add(R.id.fl_content, aboutFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(secondFragment)
                .hide(thirdFragmnet)
                .hide(aboutFragment)
                .commit()
        line_to.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_find -> {
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(secondFragment)
                        .hide(homeFragment)
                        .hide(thirdFragmnet)
                        .hide(aboutFragment)
                        .commit()
                tv_bar_title.text = "视觉是一种享受"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)

                line_to.visibility = View.VISIBLE
            }
            R.id.rb_home -> {
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(secondFragment)
                        .hide(thirdFragmnet)
                        .hide(aboutFragment)
                        .commit()
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)

                line_to.visibility = View.VISIBLE
            }
            R.id.rb_hot -> {
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(thirdFragmnet)
                        .hide(homeFragment)
                        .hide(secondFragment)
                        .hide(aboutFragment)
                        .commit()
                tv_bar_title.text = "欣赏是一种品质"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)

                line_to.visibility = View.VISIBLE
            }
            R.id.rb_mine -> {
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(aboutFragment)
                        .hide(homeFragment)
                        .hide(secondFragment)
                        .hide(thirdFragmnet)
                        .commit()
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.drawable.icon_setting)

                line_to.visibility = View.GONE
            }
        }

    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
    }

    private fun getToday(): String {
        var list = arrayOf("复杂星期日", "苦逼星期一", "忐忑星期二", "无望星期三", "挣扎星期四", "黑色星期五", "美好星期六")
        var data: Date = Date()
        var calendar: Calendar = Calendar.getInstance()
        calendar.time = data
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) {
            index = 0
        }
        return list[index]
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                toast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}

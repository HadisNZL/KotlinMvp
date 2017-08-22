package com.hadis.ktbyhadis.view.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.KeyEvent
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.view.fragment.AboutFragment
import com.hadis.ktbyhadis.view.fragment.HomeFragment
import com.hadis.ktbyhadis.view.fragment.SecondFragment
import com.hadis.ktbyhadis.view.fragment.ThirdFragment
import com.hadis.ktbyhadis.view.widget.BottomNavigationViewHelper
import com.twobbble.tools.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@RequiresApi(Build.VERSION_CODES.KITKAT)
class MainActivity : BaseActivity() {


    lateinit var mFragments: MutableList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        initFragments()

        BottomNavigationViewHelper.disableShiftMode(navigationView)

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }

        viewPager.offscreenPageLimit = 4

        navigationView.setOnNavigationItemSelectedListener { item ->
            var tab = 0
            when (item.itemId) {
                R.id.menu_android -> {
                    tab = 0
                    navigationView.setBackgroundResource(R.color.colorPrimary)
                }
                R.id.menu_ios -> {
                    tab = 1
                    navigationView.setBackgroundResource(R.color.second)
                }
                R.id.menu_girl -> {
                    tab = 2
                    navigationView.setBackgroundResource(R.color.third)
                }
                R.id.menu_about -> {
                    tab = 3
                    navigationView.setBackgroundResource(R.color.about)
                }
            }
            viewPager.currentItem = tab
            true
        }
    }

    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(HomeFragment.newInstance())
        mFragments.add(SecondFragment.newInstance())
        mFragments.add(ThirdFragment.newInstance())
        mFragments.add(AboutFragment.newInstance())
    }

    var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
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
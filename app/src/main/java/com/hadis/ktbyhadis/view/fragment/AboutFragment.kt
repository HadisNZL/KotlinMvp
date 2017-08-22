package com.hadis.ktbyhadis.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.twobbble.tools.Utils
import kotlinx.android.synthetic.main.fragment_mine.*


class AboutFragment : BaseFragment() {

    companion object {
        val ABOUT = "ABOUT"
        fun newInstance(): AboutFragment {
            val fragment = AboutFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tv_version.text = "v" + Utils.getVersion(App.instance)
    }

}

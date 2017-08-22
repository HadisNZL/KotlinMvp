package com.hadis.ktbyhadis.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.hadis.ktbyhadis.entity.VideoMol
import com.hadis.ktbyhadis.presenter.IVideoMolPresenter
import com.hadis.ktbyhadis.view.activity.VideoMoreActivity
import com.hadis.ktbyhadis.view.adapter.VideoAdapter
import com.hadis.ktbyhadis.view.api.IVideoMolView
import com.twobbble.tools.SpUtil
import kotlinx.android.synthetic.main.fragment_second.*
import org.jetbrains.anko.support.v4.toast


class SecondFragment : BaseFragment(), IVideoMolView {

    // companion object半生对象
    companion object {
        val IOS = "IOS"
        fun newInstance(): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private var mListAdapter: VideoAdapter? = null

    //数据源
    private val mLists: MutableList<VideoMol> by lazy { mutableListOf<VideoMol>() }

    private val mPresenter: IVideoMolPresenter by lazy { IVideoMolPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        getAndroid()
    }

    private fun initView() {
        mListAdapter = VideoAdapter(mLists, {
            val intent = Intent()
            intent.putExtra("name", mLists[it].name)
            intent.setClass(context, VideoMoreActivity::class.java)
            startActivity(intent)
        })

        val layoutManager = GridLayoutManager(App.instance, 2)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mListAdapter

    }

    fun getAndroid() {
        mPresenter.getVideoList()
    }

    override fun getDataSucess(ands: MutableList<VideoMol>) {
        mLists.clear()
        mLists.addAll(ands)


        mListAdapter!!.notifyDataSetChanged()
    }

    override fun getDataFailed(msg: String) {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscriber()
    }


}

package com.hadis.ktbyhadis.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.hadis.ktbyhadis.presenter.IVideoMoreMolPresenter
import com.hadis.ktbyhadis.view.adapter.VideoMoreAdapter
import com.hadis.ktbyhadis.view.api.IVideoMoreMolView
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol
import com.twobbble.tools.toast
import kotlinx.android.synthetic.main.activity_video_more.*

class VideoMoreActivity : BaseActivity(), IVideoMoreMolView, SwipeRefreshLayout.OnRefreshListener {

    var mIsRefresh: Boolean = false
    var mstart: Int = 10
    var name = ""
    private var mListAdapter: VideoMoreAdapter? = null
    private val mLists: MutableList<VideoMoreMol.ItemListBean.DataBean> by lazy {
        mutableListOf<VideoMoreMol.ItemListBean.DataBean>()
    }

    private val mPresenter: IVideoMoreMolPresenter by lazy {
        IVideoMoreMolPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_more)
        setupToolbar(toolbar)
        name = intent.getStringExtra("name")
        tv_title.text = name
        initView()
        getInitData()
    }

    private fun initView() {
        mListAdapter = VideoMoreAdapter(mLists, {
            //跳转视频详情页
            var intent: Intent = Intent(applicationContext, VideoDetailsActivity::class.java)
            var title = mLists[it].title
            var category = mLists[it].category
            var duration = mLists[it].duration
            var photoUrl = mLists[it].cover?.feed
            var desc = mLists[it].description
            var playUrl = mLists[it].playUrl
            var blurred = mLists[it].cover?.blurred
            var collect = mLists[it].consumption?.collectionCount
            var share = mLists[it].consumption?.shareCount
            var reply = mLists[it].consumption?.replyCount
            var time = System.currentTimeMillis()
            var videoBean = VideoBean(photoUrl, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
            intent.putExtra("data", videoBean as Parcelable)
            startActivity(intent)
        })

        val layoutManager = LinearLayoutManager(App.instance, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mListAdapter
        refreshLayout.setColorSchemeResources(R.color.about)
        refreshLayout.setOnRefreshListener(this)
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.i("saasda", "onScrollStateChanged---》")
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mLists.size - 1) {
                    if (!mIsRefresh) {
                        mPresenter.getVideoMoreList1(mstart, 10, name, "date")
                        mstart = mstart.plus(10)
                    }

                }
            }
        })

    }

    private fun getInitData() {
        mPresenter.getVideoMoreList(name, "date", "26868b32e808498db32fd51fb422d00175e179df", 83)
    }


    override fun getDataSucess(ands: VideoMoreMol) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mLists.size > 0) {
                mLists.clear()
            }
        }
        //forEach 遍历
        //let 默认当前这个对象作为闭包的it参数，返回值是函数里面最后一行，或者指定return
        ands.itemList?.forEach { it.data?.let { it1 -> mLists.add(it1) } }
        mListAdapter!!.notifyDataSetChanged()
    }

    override fun getDataFailed(msg: String) {
        toast(msg)
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
        }
    }

    override fun showProgress() {
        super.showProgress()
        mDialogMananer.rotateAnimLoading()
    }

    override fun hideProgress() {
        mDialogMananer.dismissAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubscriber()
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            getInitData()
        }
    }
}

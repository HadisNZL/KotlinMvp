package com.hadis.ktbyhadis.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.hadis.ktbyhadis.entity.AndMol
import com.hadis.ktbyhadis.presenter.IAndMolPresenter
import com.hadis.ktbyhadis.view.activity.DetailsActivity
import com.hadis.ktbyhadis.view.adapter.ItemAdapter
import com.hadis.ktbyhadis.view.api.IAndMolView
import com.twobbble.tools.hideErrorImg
import com.twobbble.tools.showErrorImg
import com.wingsofts.gankclient.bean.JsonResult
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast

class HomeFragment : BaseFragment(), IAndMolView, SwipeRefreshLayout.OnRefreshListener {

    // companion object半生对象
    companion object {
        val ANDROID = "Android"
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    var mIsRefresh: Boolean = false

    private var mPage = 1
    private val mPageSize = 10
    private var mListAdapter: ItemAdapter? = null

    private val mLists: MutableList<AndMol> by lazy { mutableListOf<AndMol>() }

    private val mPresenter: IAndMolPresenter by lazy { IAndMolPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        getAndroid()
    }

    private fun initView() {
        mListAdapter = ItemAdapter(mLists, {
            val intent = Intent()
            intent.putExtra("url", mLists[it].url)
            intent.setClass(context, DetailsActivity::class.java)
            startActivity(intent)
        })
        val layoutManager = LinearLayoutManager(App.instance, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = mListAdapter

        //LinearSnapHelper().attachToRecyclerView(recyclerview)//新特新

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
                        mPage++
                        getAndroid()
                    }

                }
            }
        })
    }

    fun getAndroid() {
        mPresenter.getAndroid(ANDROID, mPageSize, mPage)
    }

    override fun showProgress() {
        super.showProgress()
        mDialogMananer.rotateAnimLoading()
    }

    override fun hideProgress() {
        mDialogMananer.dismissAll()
    }

    override fun onStart() {
        super.onStart()
        Log.i("saasda", "onStart")
        mErrorLayout.setOnClickListener {
            hideErrorImg(mErrorLayout)
            getAndroid()
            Log.i("saasda", "setOnClickListener")
        }
    }

    override fun getDataSucess(ands: JsonResult<MutableList<AndMol>>) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mLists.size > 0) {
                mLists.clear()
            }

        }
        mLists.addAll(ands.results)
        mListAdapter!!.notifyDataSetChanged()
        Log.i("saasda", ands.toString())
    }

    override fun getDataFailed(msg: String) {
        toast(msg)
        if (mLists.size <= 0) {
            showErrorImg(mErrorLayout, msg, R.mipmap.img_network_error_2)
        }
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
        }
        mDialogMananer.dismissAll()
        Log.i("saasda", msg + "<----------+" + refreshLayout.isRefreshing + "+--------")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unSubscriber()
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPage = 1
            getAndroid()
            Log.i("saasda", "<=====onRefresh=======+" + refreshLayout.isRefreshing + "+--------")
        }
    }
}

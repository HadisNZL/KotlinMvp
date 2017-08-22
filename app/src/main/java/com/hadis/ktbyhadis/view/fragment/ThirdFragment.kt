package com.hadis.ktbyhadis.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.entity.AndMol
import com.hadis.ktbyhadis.presenter.IAndMolPresenter
import com.hadis.ktbyhadis.view.activity.ImageActivity
import com.hadis.ktbyhadis.view.adapter.GirlAdapter
import com.hadis.ktbyhadis.view.api.IAndMolView
import com.twobbble.tools.toast
import com.wingsofts.gankclient.bean.JsonResult
import kotlinx.android.synthetic.main.fragment_third.*


class ThirdFragment : BaseFragment(), IAndMolView, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val GIRL = "福利"
        fun newInstance(): ThirdFragment {
            val fragment = ThirdFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    var mIsRefresh: Boolean = false

    private var mPage = 1
    private val mPageSize = 20
    private var mListAdapter: GirlAdapter? = null

    //数据源
    private val mLists: MutableList<AndMol> by lazy { mutableListOf<AndMol>() }

    private val mPresenter: IAndMolPresenter by lazy { IAndMolPresenter(this) }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        getAndroid()
    }

    private fun initView() {
        mListAdapter = GirlAdapter(mLists, {
            val intent = Intent()
            intent.putExtra("url", mLists[it].url)
            intent.putExtra("who", mLists[it].who)
            intent.setClass(context, ImageActivity::class.java)
            startActivity(intent)
        })

        recyclerview.layoutManager = GridLayoutManager(activity, 2)
        recyclerview.adapter = mListAdapter

        refreshLayout.setColorSchemeResources(R.color.about)
        refreshLayout.setOnRefreshListener(this)
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
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
        mPresenter.getAndroid(GIRL, mPageSize, mPage)
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
    }

    override fun getDataFailed(msg: String) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
        }
        toast(msg)
        mDialogMananer.dismissAll()
    }

    override fun showProgress() {
        super.showProgress()
        mDialogMananer.customAnimLoading()
    }

    override fun hideProgress() {
        mDialogMananer.dismissAll()
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
        }
    }

}

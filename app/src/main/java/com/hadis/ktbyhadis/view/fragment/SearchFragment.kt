package com.hadis.ktbyhadis.view.fragment


import android.app.DialogFragment
import android.os.Bundle
import android.view.*
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.tools.KeyBoardUtils
import com.tt.lvruheng.eyepetizer.search.CircularRevealAnim
import com.twobbble.tools.toast
import kotlinx.android.synthetic.main.fragment_search.*


/**
 *  搜索页面
 */
const val SEARCH_TAG = "SearchFragment"

class SearchFragment : DialogFragment(), View.OnClickListener, CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener {

    lateinit var mCircularRevealAnim: CircularRevealAnim
    lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val metrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.98).toInt()
        val height = (metrics.heightPixels * 1.00).toInt() //DialogSearch的宽
        window.setLayout(width, height)
        window.setGravity(Gravity.TOP)
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(R.layout.fragment_search, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mCircularRevealAnim = CircularRevealAnim()

        mCircularRevealAnim.setAnimListener(this)
        iv_search_back.setOnClickListener(this)
        iv_search_search.setOnClickListener(this)
        iv_search_search.viewTreeObserver.addOnPreDrawListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_search_back -> {
                hide()
            }

            R.id.iv_search_search -> {
                toast("牛爷万岁")
            }


        }
    }

    private fun hide() {
        KeyBoardUtils.closeKeyboard(activity, et_search_keyword)
        mCircularRevealAnim.hide(iv_search_search, mRootView)
    }

    override fun onHideAnimationEnd() {
        et_search_keyword.setText("")
        dismiss()
    }

    override fun onShowAnimationEnd() {
        if (isVisible) {
            KeyBoardUtils.openKeyboard(activity, et_search_keyword)
        }
    }

    override fun onPreDraw(): Boolean {
        iv_search_search.viewTreeObserver.removeOnPreDrawListener(this);
        mCircularRevealAnim.show(iv_search_search, mRootView);
        return true
    }


}

package com.hadis.ktbyhadis.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoMoreMol


class VideoMoreAdapter(private val mDatas: List<VideoMoreMol.ItemListBean.DataBean>, val itemClick: (Int)
-> Unit) :
        RecyclerView
        .Adapter<VideoMoreAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent,
                false), viewType)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        Glide.with(App.instance).load(mDatas[position].cover?.feed)
                .crossFade()
                .into(viewHolder.iv)
        viewHolder.title.text = mDatas[position].title

        var category = mDatas[position].category
        var duration = mDatas[position].duration
        var minute = duration.div(60)
        var second = duration.minus((minute.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        viewHolder.time.text = "$category / $realMinute'$realSecond''"
        viewHolder.layout_.setOnClickListener {
            itemClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    inner class MyViewHolder(itemView: View, type: Int) : RecyclerView.ViewHolder(itemView) {

        var iv: ImageView
        var title: TextView
        var time: TextView
        var layout_: LinearLayout


        init {
            iv = itemView.findViewById(R.id.iv_photo)
            title = itemView.findViewById(R.id.tv_title)
            time = itemView.findViewById(R.id.tv_time)
            layout_ = itemView.findViewById(R.id.layout_)

        }
    }
}

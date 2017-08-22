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
import com.hadis.ktbyhadis.entity.VideoMol


class VideoAdapter(private val mDatas: List<VideoMol>, val itemClick: (Int) -> Unit) : RecyclerView
.Adapter<VideoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_find, parent,
                false), viewType)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        Glide.with(App.instance).load(mDatas[position].bgPicture)
                .crossFade()
                .into(viewHolder.iv)
        viewHolder.title.text = mDatas[position].name
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
        var layout_: LinearLayout


        init {
            iv = itemView.findViewById(R.id.iv_photo)
            title = itemView.findViewById(R.id.tv_title)
            layout_ = itemView.findViewById(R.id.layout_)

        }
    }
}

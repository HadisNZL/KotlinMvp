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
import com.hadis.ktbyhadis.entity.AndMol


class ItemAdapter(private val mDatas: List<AndMol>, val itemClick: (Int) -> Unit) : RecyclerView
.Adapter<ItemAdapter
.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fuckgoods, parent, false), viewType)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.tvTitle.text = mDatas[position].desc
        viewHolder.author.text = mDatas[position].who
        viewHolder.time.text = mDatas[position].publishedAt
        if (mDatas[position].hasImg()) {
            viewHolder.iv.visibility = View.VISIBLE
            Glide.with(App.instance).load(mDatas[position].images[0])
                    .crossFade()
                    .into(viewHolder.iv)

        } else {
            viewHolder.iv.visibility = View.GONE
        }
        viewHolder.layout_.setOnClickListener {
            itemClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    inner class MyViewHolder(itemView: View, type: Int) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var author: TextView
        var time: TextView
        var iv: ImageView
        var layout_: LinearLayout


        init {
            tvTitle = itemView.findViewById(R.id.title)
            author = itemView.findViewById(R.id.author)
            time = itemView.findViewById(R.id.time)
            iv = itemView.findViewById(R.id.image)
            layout_ = itemView.findViewById(R.id.layout_)

        }
    }
}

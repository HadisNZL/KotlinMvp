package com.hadis.ktbyhadis.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.hadis.ktbyhadis.R
import com.hadis.ktbyhadis.application.App
import com.hadis.ktbyhadis.entity.AndMol

class GirlAdapter(private val mDatas: List<AndMol>, val itemClick: (Int) -> Unit) : RecyclerView.Adapter<GirlAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_girl, parent,
                false), viewType)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        Glide.with(App.instance).load(mDatas[position].url)
                .crossFade()
                .placeholder(R.color.gray).into(viewHolder.iv)

        viewHolder.layout_.setOnClickListener {
            itemClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    inner class MyViewHolder(itemView: View, type: Int) : RecyclerView.ViewHolder(itemView) {

        var iv: ImageView
        var layout_: LinearLayout


        init {
            iv = itemView.findViewById(R.id.iv_girl)
            layout_ = itemView.findViewById(R.id.layout_)

        }
    }
}

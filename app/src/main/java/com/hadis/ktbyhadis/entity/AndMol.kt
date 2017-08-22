package com.hadis.ktbyhadis.entity

/**
 * Created by niuzilin on 2017/8/11.
 */
data class AndMol(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String


) {
    fun hasImg(): Boolean {
        return images != null
    }
}
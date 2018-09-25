package com.zll.kotlin.adapter.anko

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zhangll on 2016/12/27.
 */
abstract class ItemLayout(val context: Context) {
    abstract val view: View
    val holder: RecyclerView.ViewHolder by lazy { object : RecyclerView.ViewHolder(view) {} }
}
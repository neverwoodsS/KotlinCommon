package com.zll.kotlin.adapter.recycler

import android.view.View

/**
 * Created by zhangll on 2016/11/4.
 */
interface OnItemClickListener<T> {
    fun onItemClicked(view: View, data: T, position: Int)
}
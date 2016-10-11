package com.zll.kotlin.adapter.recycler

/**
 * Created by zhangll on 16/10/11.
 */
interface AdapterDelegate<T> {
    fun getLayoutId(): Int
    fun bind(holder: RecyclerViewHolder, data: T, position: Int)
}
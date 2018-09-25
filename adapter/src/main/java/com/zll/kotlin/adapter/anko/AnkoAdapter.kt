package com.zll.kotlin.adapter.anko

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by zhangll on 2016/12/27.
 */
class AnkoAdapter<Data, Layout : ItemLayout>(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var pool = mutableMapOf<RecyclerView.ViewHolder, Layout>()

    lateinit var dataSource: MutableList<Data>
    lateinit var onBindLayout: (layout: Layout, data: Data, position: Int) -> Unit
    lateinit var onCreateLayout: (viewType: Int) -> Layout

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =  onCreateLayout.invoke(viewType)
        pool.put(viewHolder.holder, viewHolder)
        return viewHolder.holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindLayout.invoke(pool[holder]!!, dataSource[position], position)
    }
}
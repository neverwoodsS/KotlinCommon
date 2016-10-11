package com.zll.kotlin.adapter.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zhangll on 16/10/11.
 */
class RecyclerAdapter<T>(var context: Context? = null,
                         var dataSource: MutableList<T> = mutableListOf(),
                         var layoutRes: Int = 0)
: RecyclerView.Adapter<RecyclerViewHolder>() {

    protected var listeners = SparseArray<OnItemClickListener<T>>()
    var group = Group<T>()

    fun convert(code: (convertView: View, data: T, position: Int) -> Unit) : Unit {
        group.delegates.put(0, KAdapterDelegate(layoutRes, code))
    }

    fun typeFrom(code: (T) -> Int) : Group<T> {
        group.code = code
        return group
    }

    fun refresh(dataSource: MutableList<T>) {
        try {
            this.dataSource = dataSource
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setOnItemClickListener(viewId: Int, onItemClickListener: OnItemClickListener<T>) {
        listeners.put(viewId, onItemClickListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        setOnItemClickListener(0, onItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        if (group.delegates.size() == 0) {
            return RecyclerViewHolder(inflater.inflate(layoutRes,
                    parent,
                    false))
        } else {
            println("this type = " + viewType)
            return RecyclerViewHolder(inflater.inflate(group.delegates.get(viewType).layoutRes,
                    parent,
                    false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val data = dataSource[position]
        val delegate = group.delegates.get(getItemViewType(position))
        delegate.code?.invoke(holder.itemView, data, position)
        setListeners(holder, data, position)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun getItemViewType(position: Int): Int {
        return group.code?.invoke(dataSource[position]) ?: 0
    }

    private fun setListeners(holder: RecyclerViewHolder, data: T, position: Int) {
        val count = listeners.size()
        for (i in count - 1 downTo 0) {
            val viewId = listeners.keyAt(i)
            val onItemClickListener = listeners.valueAt(i)
            holder.setOnItemClickListener(viewId, data, position, onItemClickListener)
        }
    }

    interface OnItemClickListener<T> {
        fun onItemClicked(view: View, data: T, position: Int)
    }
}
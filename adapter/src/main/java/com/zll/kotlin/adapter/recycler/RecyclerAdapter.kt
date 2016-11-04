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
class RecyclerAdapter<T>(var context: Context,
                         var dataSource: MutableList<T> = mutableListOf(),
                         var layoutRes: Int = 0)
: RecyclerView.Adapter<RecyclerViewHolder>() {

    protected var listeners = SparseArray<OnItemClickListener<T>>()
    var group = Group<T>(context)

    fun convert(code: (convertView: View, data: T, position: Int) -> Unit) : Unit {
        group.delegates.put(0, AdapterDelegate(layoutRes, code))
    }

    fun typeFrom(code: (T) -> Int) : Group<T> {
        group.code = code
        return group
    }

    fun delegate(delegate: AdapterDelegate<T>) {
        group.delegates.put(0, delegate)
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
        if (group.delegates.size() == 0) {
            val inflater = LayoutInflater.from(context)
            return RecyclerViewHolder(inflater.inflate(layoutRes,
                    parent,
                    false))
        } else {
            println("this type = " + viewType)
            return RecyclerViewHolder(group.delegates.get(viewType).view.invoke())
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val data = dataSource[position]
        val delegate = group.delegates.get(getItemViewType(position))
        delegate.code.invoke(holder.itemView, data, position)
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
}
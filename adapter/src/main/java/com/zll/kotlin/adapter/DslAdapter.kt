package com.zll.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by zhangll on 16/9/29.
 */
class DslAdapter<T> : BaseAdapter() {

    lateinit var context: Context
    lateinit var dataSource: List<T>
    var layoutRes: Int = -1
    lateinit var convert: (convertView: View, data: T, position: Int) -> Unit

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutRes, parent, false)
        convert(view, getItem(position), position)
        return view
    }

    override fun getItem(position: Int): T {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    fun convert(code: (convertView: View, data: T, position: Int) -> Unit) : Unit {
        convert = code
    }

    fun convert(type: Int, code: (convertView: View, data: T, position: Int) -> Unit) : Unit {
        convert = code
    }
}
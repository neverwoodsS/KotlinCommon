package com.zll.kotlin.adapter

import android.content.Context
import android.view.View
import com.zll.kotlin.adapter.list.ListAdapter
import com.zll.kotlin.adapter.recycler.KAdapterDelegate
import com.zll.kotlin.adapter.recycler.RecyclerAdapter

/**
 * Created by zhangll on 16/10/11.
 */
fun <T> adapterOf(context: Context, dataSource: MutableList<T>, layoutRes: Int, code: (convertView: View, data: T, position: Int) -> Unit) : ListAdapter<T> {
    val adapter = ListAdapter<T>()
    adapter.context = context
    adapter.dataSource = dataSource
    adapter.layoutRes = layoutRes
    adapter.convert = code
    return adapter
}

fun <T> adapterOf(code: ListAdapter<T>.() -> Unit) : ListAdapter<T> {
    val adapter = ListAdapter<T>()
    code(adapter)
    return adapter
}

fun <T> adapterOfRecycler(context: Context, dataSource: MutableList<T>, layoutRes: Int = 0, code: (convertView: View, data: T, position: Int) -> Unit) : RecyclerAdapter<T> {
    val adapter = RecyclerAdapter<T>()
    adapter.context = context
    adapter.dataSource = dataSource
    adapter.layoutRes = layoutRes
    adapter.convert(code)
    return adapter
}

fun <T> adapterOfRecycler(code: RecyclerAdapter<T>.() -> Unit) : RecyclerAdapter<T> {
    val adapter = RecyclerAdapter<T>()
    code(adapter)
    return adapter
}

fun <T> delegateOf(code: KAdapterDelegate<T>.() -> Unit) : KAdapterDelegate<T> {
    val delegate = KAdapterDelegate<T>()
    code(delegate)
    return delegate
}
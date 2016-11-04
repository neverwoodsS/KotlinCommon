package com.zll.kotlin.adapter

import android.content.Context
import android.view.View
import com.zll.kotlin.adapter.list.ListAdapter
import com.zll.kotlin.adapter.recycler.AdapterDelegate
import com.zll.kotlin.adapter.recycler.RecyclerAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext

/**
 * Created by zhangll on 16/10/11.
 */
fun <T> Context.adapterOf(dataSource: MutableList<T>, layoutRes: Int, code: (convertView: View, data: T, position: Int) -> Unit) : ListAdapter<T> {
    val adapter = ListAdapter<T>()
    adapter.context = this
    adapter.dataSource = dataSource
    adapter.layoutRes = layoutRes
    adapter.convert = code
    return adapter
}

inline fun <T> Context.adapterOf(code: ListAdapter<T>.() -> Unit) : ListAdapter<T> {
    val adapter = ListAdapter<T>()
    adapter.context = this
    code(adapter)
    return adapter
}

fun <T> Context.adapterOfRecycler(dataSource: MutableList<T>, layoutRes: Int = 0, code: (convertView: View, data: T, position: Int) -> Unit) : RecyclerAdapter<T> {
    val adapter = RecyclerAdapter<T>(this)
    adapter.dataSource = dataSource
    adapter.layoutRes = layoutRes
    adapter.convert(code)
    return adapter
}

inline fun <T> Context.adapterOfRecycler(code: RecyclerAdapter<T>.() -> Unit) : RecyclerAdapter<T> {
    val adapter = RecyclerAdapter<T>(this)
    code(adapter)
    return adapter
}

inline fun <T> delegateOf(code: AdapterDelegate<T>.() -> Unit) : AdapterDelegate<T> {
    val delegate = AdapterDelegate<T>()
    code(delegate)
    return delegate
}

fun <T> AdapterDelegate<T>.UI(init: AnkoContext<AdapterDelegate<T>>.() -> Unit) : AnkoContext<AdapterDelegate<T>> {
    val dsl = AnkoContextImpl(this.context, this, false)
    dsl.init()
    return dsl
}
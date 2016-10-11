package com.zll.kotlin.adapter

import android.content.Context
import android.view.View
import com.zll.hellokotlin.DslAdapter

/**
 * Created by zhangll on 16/10/11.
 */
fun <T> adapterOf(context: Context, dataSource: MutableList<T>, layoutRes: Int, code: (convertView: View, data: T, position: Int) -> Unit) : DslAdapter<T> {
    val adapter = DslAdapter<T>()
    adapter.context = context
    adapter.dataSource = dataSource
    adapter.layoutRes = layoutRes
    adapter.convert = code
    return adapter
}

fun <T> adapterOf(code: DslAdapter<T>.() -> Unit) : DslAdapter<T> {
    val adapter = DslAdapter<T>()
    code(adapter)
    return adapter
}
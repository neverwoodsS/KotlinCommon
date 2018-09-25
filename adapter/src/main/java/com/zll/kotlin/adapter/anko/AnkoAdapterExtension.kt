package com.zll.kotlin.adapter.anko

import android.content.Context

/**
 * Created by zhangll on 2016/12/27.
 */
inline fun <Data, Delegate : ItemLayout> Context.ankoAdapter(code: AnkoAdapter<Data, Delegate>.() -> Unit)
        : AnkoAdapter<Data, Delegate> {
    val adapter = AnkoAdapter<Data, Delegate>(this)
    code(adapter)
    return adapter
}
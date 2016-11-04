package com.zll.kotlin.adapter.recycler

import android.content.Context
import android.util.SparseArray

/**
 * Created by zhangll on 16/10/11.
 */
class Group<T>(var context: Context) {
    var delegates = SparseArray<AdapterDelegate<T>>()
    var code: ((T) -> Int)? = null

    fun delegate(pair: Pair<Int, AdapterDelegate<T>>) {
        pair.second.context = context
        delegates.put(pair.first, pair.second)
    }

    fun delegate(delegate: AdapterDelegate<T>) {
        delegate.context = context
        delegates.put(0, delegate)
    }

    infix fun groupBy(code: Group<T>.() -> Unit) {
        code()
    }
}
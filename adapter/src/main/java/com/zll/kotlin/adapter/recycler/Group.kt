package com.zll.kotlin.adapter.recycler

import android.util.SparseArray

/**
 * Created by zhangll on 16/10/11.
 */
class Group<T> {
    var delegates = SparseArray<AdapterDelegate<T>>()
    var code: ((T) -> Int)? = null

    fun delegate(pair: Pair<Int, AdapterDelegate<T>>) {
        delegates.put(pair.first, pair.second)
    }

    fun delegate(delegate: AdapterDelegate<T>) {
        delegates.put(0, delegate)
    }

    infix fun groupBy(code: Group<T>.() -> Unit) {
        code()
    }
}
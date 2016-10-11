package com.zll.kotlin.adapter.recycler

import android.util.SparseArray

/**
 * Created by zhangll on 16/10/11.
 */
class Group<T> {
    var delegates = SparseArray<KAdapterDelegate<T>>()
    var code: ((T) -> Int)? = null

    fun delegate(pair: Pair<Int, KAdapterDelegate<T>>) {
        delegates.put(pair.first, pair.second)
    }

    infix fun groupBy(code: Group<T>.() -> Unit) {
        code()
    }
}
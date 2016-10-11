package com.zll.kotlin.adapter.recycler

import android.view.View

/**
 * Created by zhangll on 16/10/11.
 */
class AdapterDelegate<T>(var layoutRes: Int = 0,
                         var code: ((convertView: View, data: T, position: Int) -> Unit)? = null) {
    fun convert(code: (convertView: View, data: T, position: Int) -> Unit) {
        this.code = code
    }
}
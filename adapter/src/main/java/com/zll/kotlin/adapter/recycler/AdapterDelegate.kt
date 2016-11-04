package com.zll.kotlin.adapter.recycler

import android.content.Context
import android.view.View
import android.view.ViewManager
import com.zll.kotlin.adapter.UI
import org.jetbrains.anko.AnkoContext

/**
 * Created by zhangll on 16/10/11.
 */
class AdapterDelegate<T> {
    lateinit var context: Context
    lateinit var view: () -> View
    lateinit var code: (convertView: View, data: T, position: Int) -> Unit

    constructor()

    constructor(layoutRes: Int, code: (convertView: View, data: T, position: Int) -> Unit) {
        this.view = {View.inflate(context, layoutRes, null)}
        this.code = code
    }

    fun convert(code: (convertView: View, data: T, position: Int) -> Unit) {
        this.code = code
    }

    fun view(view: () -> View) {
        this.view = view
    }
}
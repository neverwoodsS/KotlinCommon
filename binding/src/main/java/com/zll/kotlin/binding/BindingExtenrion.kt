package com.zll.hellokotlin.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.imageResource

/**
 * Created by zhangll on 16/10/9.
 */
/**
 * 基本绑定方法,其他绑定方法以此为基础
 */
fun <T: View, V, B: BindObservable<V>> T.bind(observable: B, method: T.(value: V) -> Unit) {
    method.invoke(this, observable.default)
    observable.register { method.invoke(this, it) }
}

/** View */
fun <T: View, B: BindObservable<Int>> T.bindBackgroundResource(observable: B) {
    this.bind(observable) { backgroundResource = it }
}

/** TextView */
fun <T: TextView, V: CharSequence, B: BindObservable<V>> T.bindText(observable: B) {
    this.bind(observable) { text = it }
}

/** ImageView */
fun <T: ImageView, B: BindObservable<Int>> T.bindImageResource(observable: B) {
    this.bind(observable) { imageResource = it }
}
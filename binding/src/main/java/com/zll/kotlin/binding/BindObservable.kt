package com.zll.hellokotlin.binding

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by zhangll on 16/10/9.
 */
class BindObservable<T> (val default: T)
: ReadWriteProperty<Any?, T> {

    var value : T? = null
    val subscribers: MutableList<((T) -> Unit)> = mutableListOf()

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        subscribers.forEach { it.invoke(value) }
    }

    fun set(value: T) {
        this.value = value
        subscribers.forEach { it.invoke(value) }
    }

    fun register(subscriber: (T) -> Unit) {
        subscribers.add(0, subscriber)
    }

    fun unregister(subscriber: (T) -> Unit) {
        subscribers.removeAll(mutableListOf(subscriber))
    }
}
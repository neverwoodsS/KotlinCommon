package com.zll.kotlin.binding

/**
 * Created by zhangll on 16/10/9.
 */
class BindObservable<T> (val default: T) {
    var value : T = default
    val subscribers: MutableList<((T) -> Unit)> = mutableListOf()

    fun get(): T = value

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
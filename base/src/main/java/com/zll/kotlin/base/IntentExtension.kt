package com.zll.hellokotlin.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by zhangll on 16/10/8.
 */
inline fun <reified T: Activity> Context.toActivity(
        vararg params: Pair<String, String>) {
    val intent = Intent(this, T::class.java)
    params.forEach { intent.putExtra(it.first, it.second) }
    startActivity(intent)
}
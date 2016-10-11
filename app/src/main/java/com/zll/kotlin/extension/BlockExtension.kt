package com.zll.kotlin.extension

import android.os.Build
import com.zll.kotlin.BuildConfig

/**
 * Created by zhangll on 16/10/11.
 */
inline fun supportsLollipop(code: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        code()
    }
}

inline fun handleException(code : () -> Unit) {
    try {
        code()
    } catch (e : Exception) {
        e.printStackTrace()
    }
}

inline fun debug(code: () -> Unit) {
    if (BuildConfig.DEBUG) {
        code()
    }
}
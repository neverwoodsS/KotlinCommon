package com.zll.kotlin.base

import android.os.Build
import com.zll.kotlin.base.BuildConfig

/**
 * Created by zhangll on 16/9/26.
 */
inline fun supportsLollipop(code: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        code()
    }
}

inline fun debug(code: () -> Unit) {
    if (BuildConfig.DEBUG) {
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
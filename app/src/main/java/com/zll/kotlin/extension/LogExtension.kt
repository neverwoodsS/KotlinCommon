package com.zll.kotlin.extension

import android.util.Log
import com.zll.kotlin.extension.debug
import java.util.concurrent.locks.Lock

/**
 * Created by zhangll on 16/9/26.
 */
inline fun <reified T> T.log(log: Any) {
    debug {
        Log.i(T::class.simpleName, log.toString())
    }
}

inline fun <reified T> T.warn(log: Any) {
    debug {
        Log.w(T::class.simpleName, log.toString())
    }
}

inline fun <reified T> T.error(log: Any) {
    debug {
        Log.e(T::class.simpleName, log.toString())
    }
}
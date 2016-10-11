package com.zll.kotlin.demo.binding

import com.zll.hellokotlin.binding.BindObservable
import com.zll.kotlin.R

/**
 * Created by zhangll on 16/10/9.
 */
class ViewModel {
    var text = BindObservable("null")
    var backGroundResource = BindObservable(android.R.color.black)
    var imageResource = BindObservable(R.mipmap.ic_launcher)
    var imageUrl = BindObservable("this is default imageUrl")
}
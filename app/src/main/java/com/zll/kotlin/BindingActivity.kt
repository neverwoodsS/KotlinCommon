package com.zll.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zll.hellokotlin.binding.*
import kotlinx.android.synthetic.main.activity_binding.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class BindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)

        val viewModel = ViewModel()

        //基础绑定,具体绑定方法自己实现
        contentTv.bind(viewModel.text) { text = it }

        //有针对性的调用绑定方法
        contentTv.bindText(viewModel.text)

        //使用 with 等方法,让绑定实现的代码看上去更优雅
        //支持 anko 中使用
        with(contentTv) {
            bindText(viewModel.text)
            bindBackgroundResource(viewModel.backGroundResource)
        }

        with(testIv) {
            bindImageResource(viewModel.imageResource)
            bindBackgroundResource(viewModel.backGroundResource)
        }

        async() {
            Thread.sleep(5000)
            uiThread {
                change(viewModel)
            }
        }
    }

    fun change(viewModel: ViewModel) {
        //修改 viewModel 中的值
        with(viewModel) {
            text.set("changed")
            backGroundResource.set(android.R.color.darker_gray)
            imageResource.set(android.R.color.black)
        }
    }
}
package com.zll.kotlin.demo.binding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zll.kotlin.binding.*
import com.zll.kotlin.R
import kotlinx.android.synthetic.main.activity_binding.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class BindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)

        val viewModel = ViewModel()

        //基础绑定,具体绑定方法自己实现
        textView.bind(viewModel.text) { text = it }

        //有针对性的调用绑定方法
        textView.bindText(viewModel.text)

        //使用 with 等方法,让绑定实现的代码看上去更优雅
        //支持 anko 中使用
        with(textView) {
            bindText(viewModel.text)
            bindBackgroundResource(viewModel.backGroundResource)
        }

        with(imageView) {
            bindImageResource(viewModel.imageResource)
            bindBackgroundResource(viewModel.backGroundResource)
        }

        //延时5秒修改 viewModel 中的值
        async() {
            Thread.sleep(5000)
            uiThread {
                with(viewModel) {
                    text.set("changed")
                    backGroundResource.set(android.R.color.darker_gray)
                    imageResource.set(android.R.color.black)
                }
            }
        }
    }
}
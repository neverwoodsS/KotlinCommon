package com.zll.kotlin.demo.adapter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.zll.kotlin.adapter.anko.ItemLayout
import com.zll.kotlin.adapter.anko.ankoAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(context)

                // 写 adapter
                adapter = ankoAdapter<Int, Layout> {
                    dataSource = getDataList() // 设置数据源
                    onCreateLayout =  { Layout(context) } // 创建 layout
                    onBindLayout =  { layout, data, position -> // 拿到 layout 填写参数
                        layout.titleTv.text = data.toString()
                    }
                }
            }
        }
    }

    fun getDataList(): MutableList<Int> {
        return mutableListOf(1,2,3,4,5,6,7,8,9)
    }

    class Layout(context: Context) : ItemLayout(context) {
        lateinit var logoIv: ImageView
        lateinit var titleTv: TextView

        // 写 item 布局
        override val view = context.UI {
            relativeLayout {
                logoIv = imageView {
                    padding = dip(5)
                }.lparams {
                    width = dip(40)
                    height = width
                }

                titleTv = textView {

                }.lparams {
                    width = matchParent
                    height = matchParent
                }
            }
        }.view
    }
}
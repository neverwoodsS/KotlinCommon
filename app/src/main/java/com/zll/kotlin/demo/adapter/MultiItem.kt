package com.zll.kotlin.demo.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import com.zll.kotlin.R
import com.zll.kotlin.adapter.UI
import com.zll.kotlin.adapter.delegateOf
import kotlinx.android.synthetic.main.item_multi_type_one.view.*
import kotlinx.android.synthetic.main.item_multi_type_two.view.*
import org.jetbrains.anko.*

/**
 * Created by zhangll on 16/10/11.
 */
class MultiItem(val type: Int, val text: String) {

    companion object DELEGATES {
        @SuppressLint("StaticFieldLeak")
        val DELEGATE_ONE = delegateOf<MultiItem> {
            view {
                View.inflate(context, R.layout.item_multi_type_one, null)
            }

            convert { view, data, position ->
                view.tv_item.text = data.text
                view.iv_item.imageResource = R.mipmap.ic_launcher
            }
        }

        @SuppressLint("StaticFieldLeak")
        val DELEGATE_TWO = delegateOf<MultiItem> {
            view {
                UI {
                    verticalLayout {
                        textView {
                            id = R.id.tv_item_two
                            width = dip(100)
                            height = dip(40)
                            gravity = Gravity.CENTER
                            textColor = Color.parseColor("#0000ff")
                        }.lparams {
                            verticalGravity = Gravity.CENTER
                            horizontalGravity = Gravity.CENTER
                        }
                    }
                }.view
            }

            convert { view, data, position ->
                view.tv_item_two.text = data.text
            }
        }

        fun getInt(): Int {
            return 1
        }
    }
}
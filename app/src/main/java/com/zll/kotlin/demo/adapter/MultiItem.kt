package com.zll.kotlin.demo.adapter

import com.zll.kotlin.R
import com.zll.kotlin.adapter.delegateOf
import com.zll.kotlin.adapter.recycler.AdapterDelegate
import kotlinx.android.synthetic.main.item_multi_type_one.view.*
import kotlinx.android.synthetic.main.item_multi_type_two.view.*
import org.jetbrains.anko.imageResource

/**
 * Created by zhangll on 16/10/11.
 */
class MultiItem(val type: Int, val text: String) {
    companion object DELEGATES {
        val DELEGATE_ONE = delegateOf<MultiItem> {
            layoutRes = R.layout.item_multi_type_one

            convert { view, data, position ->
                view.tv_item.text = data.text
                view.iv_item.imageResource = R.mipmap.ic_launcher
            }
        }

        val DELEGATE_TWO = delegateOf<MultiItem> {
            layoutRes = R.layout.item_multi_type_two

            convert { view, data, position ->
                view.tv_item_two.text = data.text
            }
        }

        val DELEGATE_THREE = AdapterDelegate<MultiItem>(R.layout.item_multi_type_two) {
            view, data, position ->
            view.tv_item_two.text = data.text
        }
    }
}


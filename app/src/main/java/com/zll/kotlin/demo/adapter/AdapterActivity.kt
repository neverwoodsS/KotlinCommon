package com.zll.kotlin.demo.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zll.kotlin.R
import com.zll.kotlin.adapter.adapterOf
import kotlinx.android.synthetic.main.activity_adapter.*
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class AdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)

        val numbers: MutableList<Int> = mutableListOf(1, 2, 3)

        listView.adapter = adapterOf<Int> {
            context = this@AdapterActivity
            dataSource = numbers
            layoutRes = R.layout.item_list

            convert { convertView, data, position ->
                convertView.itemTv?.text = data.toString()
                convertView.itemTv?.onClick { toast("clicked at $position")}
            }
        }

        //or

        listView.adapter = adapterOf(this, numbers, R.layout.item_list) {
            convertView, data, position ->
            convertView.itemTv?.text = data.toString()
            convertView.itemTv?.onClick { toast("clicked at $position")}
        }
    }
}
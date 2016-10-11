package com.zll.kotlin.demo.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.zll.kotlin.R
import com.zll.kotlin.adapter.adapterOfRecycler
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun onSingleClicked(view: View?) {
        val numbers = mutableListOf(1,2,3)

        recyclerView.adapter = adapterOfRecycler<Int> {
            dataSource = numbers
            layoutRes = R.layout.item_list

            convert { convertView, data, position ->
                convertView.itemTv?.text = data.toString()
                convertView.itemTv?.onClick { toast("clicked at $position")}
            }
        }

        //or

        recyclerView.adapter = adapterOfRecycler(numbers, R.layout.item_list) {
            convertView, data, position ->
            convertView.itemTv?.text = data.toString()
            convertView.itemTv?.onClick { toast("clicked at $position")}
        }
    }

    fun onMultiClicked(view: View?) {
        val datas = mutableListOf(
                MultiItem(1, "1"),
                MultiItem(2, "2"),
                MultiItem(1, "3"),
                MultiItem(2, "4"),
                MultiItem(1, "5")
        )

        recyclerView.adapter = adapterOfRecycler<MultiItem> {
            dataSource = datas

            typeFrom { it.type } groupBy {
                delegate(1 to MultiItem.DELEGATE_ONE)
                delegate(2 to MultiItem.DELEGATE_TWO)
                delegate(3 to MultiItem.DELEGATE_THREE)
            }
        }
    }
}
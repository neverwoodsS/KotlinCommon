package com.zll.kotlin.adapter.recycler

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by zhangll on 16/10/11.
 */
class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var viewCache: SparseArray<View>

    init {
        viewCache = SparseArray<View>()
    }

    fun <T : View> getChildView(id: Int): T {
        var child: View? = viewCache.get(id)
        if (child == null) {
            child = itemView.findViewById(id)
            viewCache.put(id, child)
        }
        return child as T
    }

    fun <T> setOnItemClickListener(viewId: Int,
                                   data: T,
                                   position: Int,
                                   onItemClickListener: OnItemClickListener<T>) {
        val view = if (viewId == 0) itemView else getChildView<View>(viewId)
        view?.setOnClickListener { onItemClickListener.onItemClicked(view, data, position) }
    }

    fun setText(viewId: Int, charSequence: CharSequence): RecyclerViewHolder {
        val textView = getChildView<TextView>(viewId)
        textView.text = charSequence
        return this
    }

    fun setTextColor(viewId: Int, color: Int): RecyclerViewHolder {
        val textView = getChildView<TextView>(viewId)
        textView.setTextColor(color)
        return this
    }

    fun setTextColor(viewId: Int, color: ColorStateList): RecyclerViewHolder {
        val textView = getChildView<TextView>(viewId)
        textView.setTextColor(color)
        return this
    }

    fun setTextSize(viewId: Int, textSize: Int): RecyclerViewHolder {
        val textView = getChildView<TextView>(viewId)
        textView.textSize = textSize.toFloat()
        return this
    }

    fun setImageResource(viewId: Int, imageId: Int): RecyclerViewHolder {
        val imageView = getChildView<ImageView>(viewId)
        imageView.setImageResource(imageId)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap): RecyclerViewHolder {
        val imageView = getChildView<ImageView>(viewId)
        imageView.setImageBitmap(bitmap)
        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable): RecyclerViewHolder {
        val imageView = getChildView<ImageView>(viewId)
        imageView.setImageDrawable(drawable)
        return this
    }

    fun setBackgroundColor(viewId: Int, color: Int): RecyclerViewHolder {
        getChildView<View>(viewId).setBackgroundColor(color)
        return this
    }

    fun setBackgroundResource(viewId: Int, backgroundResource: Int): RecyclerViewHolder {
        getChildView<View>(viewId).setBackgroundResource(backgroundResource)
        return this
    }

    fun setVisibility(viewId: Int, visibility: Int): RecyclerViewHolder {
        getChildView<View>(viewId).visibility = visibility
        return this
    }

    fun setTag(viewId: Int, tag: Any): RecyclerViewHolder {
        getChildView<View>(viewId).tag = tag
        return this
    }

    fun setTag(viewId: Int, tagId: Int, tag: Any): RecyclerViewHolder {
        getChildView<View>(viewId).setTag(tagId, tag)
        return this
    }

    fun setOnClickListener(viewId: Int, onClickListener: View.OnClickListener): RecyclerViewHolder {
        getChildView<View>(viewId).setOnClickListener(onClickListener)
        return this
    }

    fun setOnLongClickListener(viewId: Int, onLongClickListener: View.OnLongClickListener): RecyclerViewHolder {
        getChildView<View>(viewId).setOnLongClickListener(onLongClickListener)
        return this
    }

    fun setOnTouchListener(viewId: Int, onTouchListener: View.OnTouchListener): RecyclerViewHolder {
        getChildView<View>(viewId).setOnTouchListener(onTouchListener)
        return this
    }
}
package com.atriz.draganddropapplication.core.extensions

import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.getChildIndexFromPosition(y: Float): Int {
    forEachIndexed { index, view ->
        val prevViewPos = if (index > 0) this[index].y else 0F

        if (y in prevViewPos..(view.y + view.height)) return index
    }
    return -1
}
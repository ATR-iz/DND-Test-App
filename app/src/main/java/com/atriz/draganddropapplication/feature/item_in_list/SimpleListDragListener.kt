package com.atriz.draganddropapplication.feature.item_in_list

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atriz.draganddropapplication.core.BaseDragListener
import com.atriz.draganddropapplication.core.extensions.getChildIndexFromPosition
import com.atriz.draganddropapplication.core.ui.RecyclerViewAdapter

open class SimpleListDragListener: BaseDragListener() {

    open var selectedItem: View? = null

    override fun onDragLocation(view: View, event: DragEvent) {
        val position = (view as RecyclerView).getChildIndexFromPosition(event.y)

        checkSelectedItem(view, position)
        checkMoveItem(view, position)
    }

    override fun onDragEnded(view: View, event: DragEvent) {
        selectedItem?.alpha = DRAG_FINISH_ALPHA
        selectedItem = null
        view.invalidate()
    }

    open fun checkSelectedItem(recyclerView: RecyclerView, position: Int) {
        val item = recyclerView.getChildAt(position)
        if (selectedItem == null) {
            selectedItem = item
            selectedItem?.alpha = DRAG_START_ALPHA
        }
    }

    open fun checkMoveItem(recyclerView: RecyclerView, position: Int) {
        selectedItem?.let { view ->
            val selectedItemPosition = recyclerView.getChildLayoutPosition(view)
            if (position != -1 && position != selectedItemPosition) {
                (recyclerView.adapter as RecyclerViewAdapter).moveItem(
                    selectedItemPosition,
                    position
                )
            }
        }
    }
}
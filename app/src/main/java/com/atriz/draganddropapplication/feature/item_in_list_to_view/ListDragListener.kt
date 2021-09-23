package com.atriz.draganddropapplication.feature.item_in_list_to_view

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atriz.draganddropapplication.core.data.BankCard
import com.atriz.draganddropapplication.core.extensions.getChildIndexFromPosition
import com.atriz.draganddropapplication.core.ui.RecyclerViewAdapter
import com.atriz.draganddropapplication.feature.item_in_list.SimpleListDragListener

class ListDragListener: SimpleListDragListener() {

    override var selectedItem: View? = null

    override fun onDragLocation(view: View, event: DragEvent) {
        val position = (view as RecyclerView).getChildIndexFromPosition(event.y)
        checkContainsItem(view, event)
        checkMoveItem(view, position)

        selectedItem?.alpha = DRAG_START_ALPHA

        view.invalidate()
    }

    override fun onDragEnded(view: View, event: DragEvent) {
        selectedItem?.alpha = DRAG_FINISH_ALPHA
        selectedItem = null
        view.invalidate()
    }

    private fun checkContainsItem(recyclerView: RecyclerView, event: DragEvent) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter
        val bankCard = event.localState as BankCard
        val isContainsItem = adapter.contains(bankCard)

        if (!isContainsItem) {
            adapter.addItem(bankCard)
        } else {
            val position = adapter.getItemPosition(bankCard)
            checkSelectedItem(recyclerView, position)
        }
    }
}
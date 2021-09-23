package com.atriz.draganddropapplication.feature.item_in_list_to_list

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atriz.draganddropapplication.core.data.BankCard
import com.atriz.draganddropapplication.core.extensions.getChildIndexFromPosition
import com.atriz.draganddropapplication.core.ui.RecyclerViewAdapter
import com.atriz.draganddropapplication.feature.item_in_list.SimpleListDragListener

class ListDragListener(
    private val recyclerViewHolder: RecyclerViewHolder,
) : SimpleListDragListener() {

    override var selectedItem: View? = null

    override fun onDragEntered(view: View, event: DragEvent) {
        (view as? RecyclerView)?.let { recyclerView ->
            checkDeleteItem(recyclerView, event)
        }
    }

    override fun onDragLocation(view: View, event: DragEvent) {
        (view as? RecyclerView)?.let { recyclerView ->
            val position = recyclerView.getChildIndexFromPosition(event.y)

            checkContainsItem(recyclerView, event)
            checkMoveItem(recyclerView, position)
        }
    }

    override fun onDragEnded(view: View, event: DragEvent) {
        selectedItem?.viewTreeObserver?.dispatchOnDraw()
        selectedItem?.alpha = DRAG_FINISH_ALPHA
        selectedItem = null
        recyclerViewHolder.recyclerView = null
    }

    override fun checkSelectedItem(recyclerView: RecyclerView, position: Int) {
        if (selectedItem == null) {
            val item = recyclerView.getChildAt(position)

            recyclerViewHolder.recyclerView = recyclerView
            selectedItem = item

            selectedItem?.alpha = DRAG_START_ALPHA

            selectedItem?.viewTreeObserver?.addOnDrawListener {
                selectedItem?.alpha = DRAG_START_ALPHA
            }
        }
    }

    private fun checkDeleteItem(recyclerView: RecyclerView, event: DragEvent) {
        if (recyclerView != recyclerViewHolder.recyclerView && recyclerViewHolder.recyclerView != null) {
            val oldAdapter = recyclerViewHolder.recyclerView?.adapter as? RecyclerViewAdapter
            val bankCard = event.localState as BankCard
            oldAdapter?.removeItem(bankCard)
        }
    }

    private fun checkContainsItem(recyclerView: RecyclerView, event: DragEvent) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter
        val bankCard = event.localState as BankCard
        val isContainsItem = adapter.contains(bankCard)

        if (!isContainsItem) {
            val position = recyclerView.getChildIndexFromPosition(event.y)

            if (position == -1) {
                adapter.addItem(bankCard)
            } else {
                adapter.addItem(bankCard, position)
            }

            selectedItem = null
        } else {
            val position = adapter.getItemPosition(bankCard)
            checkSelectedItem(recyclerView, position)
        }
    }
}
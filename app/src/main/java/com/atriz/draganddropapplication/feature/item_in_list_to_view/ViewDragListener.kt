package com.atriz.draganddropapplication.feature.item_in_list_to_view

import android.view.DragEvent
import android.view.View
import com.atriz.draganddropapplication.core.BaseDragListener
import com.atriz.draganddropapplication.core.data.BankCard

class ViewDragListener: BaseDragListener() {

    override fun onDragLocation(view: View, event: DragEvent) {
        (view as? BankCardView)
            ?.setBankCard(event.localState as BankCard)
    }

    override fun onDragExited(view: View, event: DragEvent) {
        (view as? BankCardView)?.setBankCard(BankCard("","",""))
    }
}
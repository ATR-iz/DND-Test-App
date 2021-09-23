package com.atriz.draganddropapplication.core

import android.view.DragEvent
import android.view.View

open class BaseDragListener: View.OnDragListener {
    override fun onDrag(view: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DRAG_LOCATION -> onDragLocation(view, event)
            DragEvent.ACTION_DRAG_ENDED -> onDragEnded(view, event)
            DragEvent.ACTION_DRAG_ENTERED -> onDragEntered(view, event)
            DragEvent.ACTION_DRAG_EXITED -> onDragExited(view, event)
            DragEvent.ACTION_DRAG_STARTED -> onDragStarted(view, event)
            DragEvent.ACTION_DROP -> onDrop(view, event)
        }

        return true
    }

    open fun onDragStarted(view: View, event: DragEvent) = Unit

    open fun onDragEnded(view: View, event: DragEvent) = Unit

    open fun onDragEntered(view: View, event: DragEvent) = Unit

    open fun onDragExited(view: View, event: DragEvent) = Unit

    open fun onDragLocation(view: View, event: DragEvent) = Unit

    open fun onDrop(view: View, event: DragEvent) = Unit

    companion object {
        const val DRAG_START_ALPHA = 0.3F
        const val DRAG_FINISH_ALPHA = 1F
    }
}
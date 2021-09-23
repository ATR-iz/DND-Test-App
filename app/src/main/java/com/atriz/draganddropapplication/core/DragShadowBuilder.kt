package com.atriz.draganddropapplication.core

import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View

class DragShadowBuilder(view: View) : View.DragShadowBuilder(view) {
    private var shadow: Drawable

    init {
        view.buildDrawingCache()
        shadow = BitmapDrawable(view.drawingCache)
    }

    override fun onProvideShadowMetrics(size: Point, touch: Point) {
        val width = view.width
        val height = view.height

        shadow.setBounds(0, 0, width, height)
        size.set(width, height)
        touch.set(width / 2, height / 2)
    }

    override fun onDrawShadow(canvas: Canvas) {
        shadow.draw(canvas)
    }
}
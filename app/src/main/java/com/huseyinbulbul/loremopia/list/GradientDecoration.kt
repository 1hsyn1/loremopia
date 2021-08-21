package com.huseyinbulbul.loremopia.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.R

class GradientDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var backgroundDrawable: Drawable? = null

    init {
        backgroundDrawable = context.getDrawable(R.drawable.bg_list_gradient)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        backgroundDrawable?.let { bg ->
            if (parent.layoutManager == null) {
                return
            }

            val left = parent.paddingLeft
            val top = -parent.computeVerticalScrollOffset()
            val right = parent.width - parent.paddingRight
            val bottom = parent.computeVerticalScrollRange() + top

            bg.bounds = Rect(left, top, right, bottom)
            bg.draw(canvas)
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) = outRect.set(0, 0, 0, 0)

}


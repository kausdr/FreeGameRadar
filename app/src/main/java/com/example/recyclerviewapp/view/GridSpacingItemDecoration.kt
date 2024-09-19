package com.example.recyclerviewapp.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) % spanCount

        if (includeEdge) {
            outRect.left = spacing - position * spacing / spanCount
            outRect.right = (position + 1) * spacing / spanCount
            if (parent.getChildAdapterPosition(view) < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = position * spacing / spanCount
            outRect.right = spacing - (position + 1) * spacing / spanCount
            if (parent.getChildAdapterPosition(view) >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}

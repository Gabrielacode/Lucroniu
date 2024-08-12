package com.allstars.lucroniu.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import kotlin.math.max
import kotlin.math.min


class ButtonBehaviour<V :View> (context: Context, attrs: AttributeSet): CoordinatorLayout.Behavior<V> (context,attrs){


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {

        return axes == ViewCompat.SCROLL_AXIS_VERTICAL

    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {


        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        val scale_rate :Float = 150f
        Log.i("Behaviour","Value $dy")
        val dyAsFloat = dy.toFloat()

        //If the dy is minus then

        child.scaleY = if(dyAsFloat<0) min(1.0f,child.scaleY- dy/scale_rate) else max(0.0f,child.scaleY- dy/scale_rate)
        child.scaleX= if(dyAsFloat<0) min(1.0f,child.scaleX- dy/scale_rate) else max(0.0f,child.scaleX- dy/scale_rate)



    }



}
// In coordinator Layout the behaviours recieve events event b4 the target view  wow
// Translation is different from the layout position this is used for animations and is for post layout drawings that means the view is not actually there but just the drawing


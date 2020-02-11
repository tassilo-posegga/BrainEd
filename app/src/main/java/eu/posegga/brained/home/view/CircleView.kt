package eu.posegga.brained.home.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CircleView(context: Context?) : View(context) {

    var radius = 50f
    var circleColor = Color.BLACK
        set(value) {
            field = value
            paint.color = value
            invalidate()
        }

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = circleColor
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }
}

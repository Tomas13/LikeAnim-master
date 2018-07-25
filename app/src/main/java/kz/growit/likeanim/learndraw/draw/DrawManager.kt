package kz.growit.likeanim.learndraw.draw

import android.content.Context
import android.graphics.Canvas
import kz.growit.likeanim.learndraw.animation.AnimationValue
import kz.growit.likeanim.learndraw.model.Chart

class DrawManager(context: Context) {

    private val controller: DrawController
    private val chart: Chart = Chart()

    init {
        controller = DrawController(context, chart)
    }

    fun chart(): Chart {
        return chart
    }

    fun draw(canvas: Canvas) {
        controller.draw(canvas)
    }

    fun updateValue(value: AnimationValue) {
        controller.updateValue(value)
    }
}

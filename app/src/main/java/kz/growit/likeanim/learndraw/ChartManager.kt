package kz.growit.likeanim.learndraw

import android.content.Context
import kz.growit.likeanim.learndraw.animation.AnimationManager
import kz.growit.likeanim.learndraw.animation.AnimationValue
import kz.growit.likeanim.learndraw.draw.DrawManager
import kz.growit.likeanim.learndraw.model.Chart

class ChartManager(context: Context, private val listener: AnimationListener?) : AnimationManager.AnimationListener {

    private val drawManager: DrawManager = DrawManager(context)
    private val animationManager: AnimationManager

    interface AnimationListener {
        fun onAnimationUpdated()
    }

    init {
        this.animationManager = AnimationManager(drawManager.chart(), this)
    }

    fun chart(): Chart {
        return drawManager.chart()
    }

    fun drawer(): DrawManager {
        return drawManager
    }

    fun animate() {
        if (!drawManager.chart().drawData.isEmpty()) {
            animationManager.animate()
        }
    }

    override fun onAnimationUpdated(value: AnimationValue) {
        drawManager.updateValue(value)
        listener?.onAnimationUpdated()
    }
}

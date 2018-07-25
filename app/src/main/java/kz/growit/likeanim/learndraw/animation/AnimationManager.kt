package kz.growit.likeanim.learndraw.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import kz.growit.likeanim.learndraw.model.Chart
import kz.growit.likeanim.learndraw.model.DrawData
import java.util.*

class AnimationManager(private val chart: Chart, private val listener: AnimationListener?) {
    private val animatorSet: AnimatorSet = AnimatorSet()
    private var lastValue: AnimationValue? = null

    private val runningAnimationPosition: Int
        get() {
            val childAnimations = animatorSet.childAnimations
            for (i in childAnimations.indices) {
                val animator = childAnimations[i]
                if (animator.isRunning) {
                    return i
                }
            }

            return VALUE_NONE
        }

    interface AnimationListener {
        fun onAnimationUpdated(value: AnimationValue)
    }

    fun animate() {
        this.animatorSet.playSequentially(createAnimatorList())
        animatorSet.start()
    }

    private fun createAnimatorList(): List<Animator> {
        val dataList = chart.drawData
        val animatorList = ArrayList<Animator>()

        for (drawData in dataList) {
            animatorList.add(createAnimator(drawData))
        }
        return animatorList
    }

    private fun createAnimator(drawData: DrawData): ValueAnimator {
        val propertyX = PropertyValuesHolder.ofInt(PROPERTY_X, drawData.startX, drawData.stopX)
        val propertyY = PropertyValuesHolder.ofInt(PROPERTY_Y, drawData.startY, drawData.stopY)
        val propertyAlpha = PropertyValuesHolder.ofInt(PROPERTY_ALPHA, ALPHA_START, ALPHA_END)

        val animator = ValueAnimator()
        animator.setValues(propertyX, propertyY, propertyAlpha)
        animator.duration = ANIMATION_DURATION.toLong()
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener { valueAnimator -> this@AnimationManager.onAnimationUpdate(valueAnimator) }

        return animator
    }

    private fun onAnimationUpdate(valueAnimator: ValueAnimator?) {
        if (valueAnimator == null || listener == null) {
            return
        }

        val x = valueAnimator.getAnimatedValue(PROPERTY_X) as Int
        val y = valueAnimator.getAnimatedValue(PROPERTY_Y) as Int
        val alpha = valueAnimator.getAnimatedValue(PROPERTY_ALPHA) as Int
        val runningAnimationPosition = runningAnimationPosition

        val value = AnimationValue()
        value.x = x
        value.y = y
        value.alpha = adjustAlpha(runningAnimationPosition, alpha)
        value.runningAnimationPosition = runningAnimationPosition

        listener.onAnimationUpdated(value)
        lastValue = value
    }

    private fun adjustAlpha(runningPos: Int, alpha: Int): Int {
        if (lastValue == null) {
            return alpha
        }

        val isPositionIncreased = runningPos > lastValue!!.runningAnimationPosition
        val isAlphaIncreased = alpha > lastValue!!.alpha

        return if (!isPositionIncreased && !isAlphaIncreased) {
            lastValue!!.alpha
        } else {
            alpha
        }
    }

    companion object {

        const val PROPERTY_X = "PROPERTY_X"
        const val PROPERTY_Y = "PROPERTY_Y"
        const val PROPERTY_ALPHA = "PROPERTY_ALPHA"

        const val VALUE_NONE = -1
        const val ALPHA_START = 0
        const val ALPHA_END = 255
        private const val ANIMATION_DURATION = 250
    }
}

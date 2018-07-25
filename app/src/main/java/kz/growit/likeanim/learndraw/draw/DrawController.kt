package kz.growit.likeanim.learndraw.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import kz.growit.likeanim.learndraw.ValueUtils
import kz.growit.likeanim.learndraw.animation.AnimationManager
import kz.growit.likeanim.learndraw.animation.AnimationValue
import kz.growit.likeanim.learndraw.model.Chart

class DrawController(private val context: Context, private val chart: Chart) {
    private var value: AnimationValue? = null

    private var frameLinePaint: Paint? = null
    private var frameInternalPaint: Paint? = null
    private var frameTextPaint: Paint? = null

    private var linePaint: Paint? = null
    private var strokePaint: Paint? = null
    private var fillPaint: Paint? = null

    private val titleWidth: Int
        get() {
            val valueList = chart.inputData
            if (valueList == null || valueList.isEmpty()) {
                return 0
            }

            val maxValue = ValueUtils.max(valueList).toString()
            val titleWidth = frameTextPaint!!.measureText(maxValue).toInt()
            val padding = chart.padding

            return padding + titleWidth + padding
        }

    init {
        init()
    }

    fun updateTitleWidth() {
        val titleWidth = titleWidth
        chart.titleWidth = titleWidth
    }

    fun updateValue(value: AnimationValue) {
        this.value = value
    }

    fun draw(canvas: Canvas) {
        drawChart(canvas)
    }

    private fun drawChart(canvas: Canvas) {
        val runningAnimationPosition = if (value != null) value!!.runningAnimationPosition else AnimationManager.VALUE_NONE

        for (i in 0 until runningAnimationPosition) {
            drawChart(canvas, i, false)
        }

        if (runningAnimationPosition > AnimationManager.VALUE_NONE) {
            drawChart(canvas, runningAnimationPosition, true)
        }
    }

    private fun drawChart(canvas: Canvas, position: Int, isAnimation: Boolean) {
        val dataList = chart.drawData
        if (dataList == null || position > dataList.size - 1) {
            return
        }

        val drawData = dataList[position]
        val startX = drawData.startX
        val startY = drawData.startY

        val stopX: Int
        val stopY: Int
        val alpha: Int

        if (isAnimation) {
            stopX = value!!.x
            stopY = value!!.y
            alpha = value!!.alpha

        } else {
            stopX = drawData.stopX
            stopY = drawData.stopY
            alpha = AnimationManager.ALPHA_END
        }

        drawChart(canvas, startX, startY, stopX, stopY, alpha, position)
    }

    private fun drawChart(canvas: Canvas, startX: Int, startY: Int, stopX: Int, stopY: Int, alpha: Int, position: Int) {
//        val radius = chart.radius
//        val inerRadius = chart.inerRadius
        canvas.drawLine(startX.toFloat(), startY.toFloat(), stopX.toFloat(), stopY.toFloat(), linePaint!!)

//        if (position > 0) {
//            strokePaint!!.alpha = alpha
//            canvas.drawCircle(startX.toFloat(), startY.toFloat(), radius.toFloat(), strokePaint!!)
//            canvas.drawCircle(startX.toFloat(), startY.toFloat(), inerRadius.toFloat(), fillPaint!!)
//        }
    }


    private fun init() {
        val res = context.resources
        chart.heightOffset = 9
        chart.padding = 8


//        fillPaint!!.style = Paint.Style.FILL
        linePaint = Paint()
        linePaint!!.isAntiAlias = true
        linePaint!!.strokeWidth = 8f
        linePaint!!.color = res.getColor(android.R.color.holo_blue_light)
    }}



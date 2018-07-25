package com.rd.chartview.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import kz.growit.likeanim.learndraw.ChartManager
import kz.growit.likeanim.learndraw.ValueUtils
import kz.growit.likeanim.learndraw.model.InputData
import java.util.*

class ChartView : View, ChartManager.AnimationListener {

    private var chartManager: ChartManager? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec) / 2
        chartManager!!.chart().width = width
        chartManager!!.chart().height = height
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        chartManager!!.drawer().draw(canvas)
    }

    override fun onAnimationUpdated() {
        invalidate()
    }

    fun setData(dataList: List<InputData>?) {
        var dataList = dataList
        if (dataList == null) {
            dataList = ArrayList<InputData>()
        }

        val chart = chartManager!!.chart()
        chart.inputData = dataList

        post {
            chart.drawData = ValueUtils.getDrawData(chart)
            chartManager!!.animate()
        }
    }

    private fun init() {
        chartManager = ChartManager(context, this)
    }
}

package kz.growit.likeanim.view

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chart.*
import kz.growit.likeanim.R
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.view.LineChartView
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import android.support.constraint.ConstraintLayout
import android.view.animation.AnimationUtils


class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val values = ArrayList<PointValue>()
        values.add(PointValue(0f, 2f))
        values.add(PointValue(1f, 4f))
        values.add(PointValue(2f, 3f))
        values.add(PointValue(3f, 4f))

        //In most cased you can call data model methods in builder-pattern-like manner.
        val line = Line(values).setColor(Color.BLUE).setCubic(true)
        val lines = ArrayList<Line>()
        lines.add(line)

        val data = LineChartData()
        data.lines = lines

        val chart = LineChartView(this)
        chart.lineChartData = data

        linear_chart.addView(chart)


        val anim = ValueAnimator.ofInt(0, 359)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = chart.getLayoutParams() as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = `val`.toFloat()
            chart.setLayoutParams(layoutParams)
        }
        anim.duration = 3000
        anim.interpolator = LinearInterpolator()
        anim.repeatMode = ValueAnimator.RESTART
        anim.repeatCount = ValueAnimator.INFINITE

//        chart.startAnimation(AnimationUtils)
        chart.animation.duration = 2000
        chart.animation.start()

    }
}

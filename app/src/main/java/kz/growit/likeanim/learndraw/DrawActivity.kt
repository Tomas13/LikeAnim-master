package kz.growit.likeanim.learndraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rd.chartview.view.ChartView
import kz.growit.likeanim.learndraw.model.InputData
import java.util.*


class DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_draw)
        val drawView = DrawView(this)

        val chartView = ChartView(this)
        setContentView(chartView)

        val dataList = createChartData()
        chartView.setData(dataList)
    }

    private fun createChartData(): List<InputData> {
        val dataList = ArrayList<InputData>()
//        dataList.add(InputData(10))
//        dataList.add(InputData(50))
        dataList.add(InputData(50))
//        dataList.add(InputData(20))
//        dataList.add(InputData(50))
//        dataList.add(InputData(40))

        return dataList
    }


    class DrawView(context: Context) : View(context) {
        val p = Paint()
        val text = "Draw the text, with origin at (x,y), using the specified paint"
        val path = Path()

        override fun onDraw(canvas: Canvas) {


            // создаем крест в path
            path.reset()
            path.lineTo(100f, 250f)

            // рисуем path зеленым
            p.color = Color.GREEN
            canvas.drawPath(path, p)

            // настраиваем матрицу на перемещение на 300 вправо и 200 вниз
            matrix.reset()
            matrix.setTranslate(300f, 200f)

            // применяем матрицу к path
            path.transform(matrix)

            // рисуем path синим
            p.color = Color.BLUE
            canvas.drawPath(path, p)


            canvas.drawLine(100f, 50f, 100f, 250f, p)

        }
    }

}

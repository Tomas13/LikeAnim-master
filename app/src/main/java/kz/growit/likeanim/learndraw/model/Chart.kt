package kz.growit.likeanim.learndraw.model

import java.util.ArrayList

class Chart {

    var width: Int = 0
    var height: Int = 0

    var padding: Int = 0
    var titleWidth: Int = 0
    var textSize: Int = 0
    var heightOffset: Int = 0

//    var radius: Int = 0
//    var inerRadius: Int = 0

    var inputData: List<InputData> = ArrayList()
    var drawData: List<DrawData> = ArrayList()

    companion object {

        val CHART_PARTS = 5
        val MAX_ITEMS_COUNT = 7
        val CHART_PART_VALUE = 10
        val TEXT_SIZE_OFFSET = 10
    }
}

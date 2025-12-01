package twentyTwentyFive.dayOne

import kotlin.math.abs

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val regex = Regex("([LR])(\\d+)")
            var currentDial = 50
            var totalZeroPointings = 0
            lines.forEach { it ->
                val matchResult = regex.find(it)
                val (rotation, amount) = matchResult!!.destructured

                currentDial = if (rotation == "L") {
                    val tempDial = currentDial - amount.toInt()
                    var hundreds = abs(Math.floorDiv(tempDial, 100))
                    if (tempDial.mod(100) == 0) hundreds++ // lands on zero
                    if (currentDial == 0 && tempDial.mod(100) != 0  && hundreds > 0) hundreds-- // dont count flip if starting at zero
                    totalZeroPointings += hundreds

                    (currentDial - amount.toInt()).mod(100)
                } else {
                    val tempDial = currentDial + amount.toInt()
                    val hundreds = Math.floorDiv(tempDial, 100)
                    totalZeroPointings += hundreds

                    (currentDial + amount.toInt()).mod(100)
                }
            }
            println("total zero pointings: $totalZeroPointings")
        }
    }
}
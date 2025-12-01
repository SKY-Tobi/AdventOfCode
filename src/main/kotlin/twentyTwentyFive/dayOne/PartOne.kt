package twentyTwentyFive.dayOne

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val regex = Regex("([LR])(\\d+)")
            var currentDial = 50
            var totalZeroPointings = 0
            lines.forEach { it ->
                val matchResult = regex.find(it)
                val (rotation, amount) = matchResult!!.destructured

                currentDial = if (rotation == "L") {
                    (currentDial - amount.toInt()).mod(100)
                } else {
                    (currentDial + amount.toInt()).mod(100)
                }

                if(currentDial == 0) {
                    totalZeroPointings++
                }
            }
            println("total zero pointings: $totalZeroPointings")
        }
    }
}
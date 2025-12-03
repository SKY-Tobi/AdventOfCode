package twentyTwentyFive.dayThree

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var total = 0
            lines.forEach { banks ->
                var highestTenthDigit = 0
                var highestDigit = 0
                banks.forEachIndexed { index, it ->
                    val currentDigit = it.digitToInt()
                    if (currentDigit > highestTenthDigit && index != banks.lastIndex) {
                        highestTenthDigit = currentDigit
                        highestDigit = 0
                    } else if (currentDigit > highestDigit) {
                        highestDigit = currentDigit
                    }
                }
                total += highestTenthDigit * 10 + highestDigit
            }
            println("total joltage: $total")
        }
    }
}
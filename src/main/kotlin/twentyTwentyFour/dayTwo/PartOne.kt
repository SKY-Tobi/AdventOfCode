package twentyTwentyFour.dayTwo

import kotlin.math.abs

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val amountOfSafeReports = lines.map { line ->
                val levels = line.split(" ").map { it.toInt() }
                val increasingType =
                    if (levels[0] - levels[1] > 0) LevelIncrementTypeEnum.DECREASING else LevelIncrementTypeEnum.INCREASING

                when (increasingType) {
                    LevelIncrementTypeEnum.INCREASING -> {
                        checkAdjacentIncreasingElements(levels)
                    }

                    LevelIncrementTypeEnum.DECREASING -> {
                        checkAdjacentDecreasingElements(levels)
                    }

                    LevelIncrementTypeEnum.DELETE -> false
                }
            }.count { it }

            println("amount of safe reports: $amountOfSafeReports")
        }

        private fun checkAdjacentIncreasingElements(list: List<Int>): Boolean {
            return list.windowed(2).all { (current, next) ->
                current < next && abs(current - next) <= 3
            }
        }

        private fun checkAdjacentDecreasingElements(list: List<Int>): Boolean {
            return list.windowed(2).all { (current, next) ->
                current > next && abs(current - next) <= 3
            }
        }
    }
}

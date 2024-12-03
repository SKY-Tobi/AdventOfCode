package twentyTwentyFour.dayTwo

import kotlin.math.abs

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val amountOfSafeReports = lines.count { line ->
                val levels = line.split(" ").map { it.toInt() }
                isSafe(levels) || levels.indices.any { i ->
                    isSafe(levels.filterIndexed { index, _ -> index != i })
                }
            }

            println("amount of safe reports: $amountOfSafeReports")
        }

        private fun isSafe(list: List<Int>): Boolean {
            return checkAdjacentIncreasingElements(list) || checkAdjacentDecreasingElements(list)
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

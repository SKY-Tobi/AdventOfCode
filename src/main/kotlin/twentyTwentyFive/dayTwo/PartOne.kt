package twentyTwentyFive.dayTwo

import java.util.stream.LongStream.range

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val ranges = lines[0].split(",").map {
                val (from, to) = it.split("-")
                Pair(from.toLong(), to.toLong())
            }
            var result = 0L
            ranges.forEach {
                range(it.first, it.second + 1L).forEach numberCheckLoop@ { numberToCheck ->
                    val numberToCheckString = numberToCheck.toString()
                    var digitsToCheck = ""
                    numberToCheckString.forEach { digit ->
                        digitsToCheck += digit
                        if(digitsToCheck.length > numberToCheckString.length / 2) return@numberCheckLoop
                        if (numberToCheckString.matches(Regex("^($digitsToCheck){2}$"))) {
                            result += numberToCheckString.toLong()
                            return@numberCheckLoop
                        }
                    }
                }
            }
            println("total of Invalid Ids: $result")
        }
    }
}
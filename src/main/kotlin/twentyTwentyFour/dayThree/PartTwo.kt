package twentyTwentyFour.dayThree

class PartTwo {
    companion object {
        private const val DO = "do()"
        private const val DONT = "don't()"

        fun execute(lines: List<String>) {
            val regexMul = Regex("""mul\(\d+,\d+\)|do\(\)|don't\(\)""")
            val regexNumbers = Regex("""\d+""")
            var multiplicationEnabled = true

            val numbers = lines.flatMap { line ->
                extractMatchingStrings(line, regexMul).mapNotNull {
                    when (it) {
                        DO -> {
                            multiplicationEnabled = true
                            null
                        }
                        DONT -> {
                            multiplicationEnabled = false
                            null
                        }
                        else -> if (multiplicationEnabled) {
                            val numbers = extractMatchingStrings(it, regexNumbers)
                            Pair(numbers[0].toInt(), numbers[1].toInt())
                        } else {
                            null
                        }
                    }
                }
            }

            val sum = numbers.fold(0) { acc, (a, b) -> acc + a * b }

            println("total distance: $sum")
        }

        private fun extractMatchingStrings(text: String, regex: Regex): List<String> {
            return regex.findAll(text).map { it.value }.toList()
        }
    }
}

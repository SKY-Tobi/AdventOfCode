package twentyTwentyFour.dayThree

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val regexMul = Regex("""mul\(\d+,\d+\)""")
            val regexNumbers = Regex("""\d+""")
            val numbers = lines.map { line ->
                extractMatchingStrings(line, regexMul)
            }.flatten().map {
                extractMatchingStrings(it, regexNumbers)
            }.map { Pair(it[0].toInt(), it[1].toInt()) }

            val sum = numbers.fold(0) { acc, (a, b) -> acc + a * b }

            println("total distance: $sum")
        }

        private fun extractMatchingStrings(text: String, regex: Regex): List<String> {
            return regex.findAll(text).map { it.value }.toList()
        }
    }
}

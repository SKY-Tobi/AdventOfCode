package twentyTwentyFour.dayFive

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val rules = mutableMapOf<Int, List<Int>>()
            val pageNumberUpdates = mutableListOf<List<Int>>()

            val parts = lines.joinToString("\n").split("\n\n")
            parts[0].lines().forEach { readFirstPart(it, rules) }
            parts[1].lines().forEach { readSecondPart(it, pageNumberUpdates) }

            val sum = pageNumberUpdates.filter { row ->
                row.indices.all { index ->
                    // None of the pages before (in row) should be in the list of pages that should be after the current page
                    row.subList(0, index).none { rules[row[index]]?.contains(it) == true }
                }
            }.sumOf { it[it.size / 2] }

            println("sum: $sum")
        }

        private fun readFirstPart(it: String, rules: MutableMap<Int, List<Int>>) {
            val numbers = it.split(Regex("\\|")).map(String::toInt)
            rules.merge(numbers[0], listOf(numbers[1])) { old, new -> old + new }
        }

        private fun readSecondPart(it: String, pageNumberUpdates: MutableList<List<Int>>) {
            pageNumberUpdates.add(it.split(Regex("\\D+")).map(String::toInt))
        }
    }
}
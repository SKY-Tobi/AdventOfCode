package twentyTwentyFour.dayFive

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var isReadingFirstPart = true

            val rules = mutableMapOf<Int, List<Int>>()
            val pageNumberUpdates = mutableListOf<List<Int>>()

            lines.forEach {
                if (isReadingFirstPart) {
                    if (it.trim().isEmpty()) {
                        isReadingFirstPart = false
                        return@forEach
                    }
                    readFirstPart(it, rules)
                } else {
                    readSecondPart(it, pageNumberUpdates)
                }
            }

            var sum = 0

            pageNumberUpdates.forEach { row ->
                val correct = row.indices.all { index ->
                    // None of the pages before should be in the list of pages that should be after the current page
                    row.subList(0, index).none { rules[row[index]]?.contains(it) == true }
                }

                if (correct) {
                    sum += row[row.size / 2]
                }
            }

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
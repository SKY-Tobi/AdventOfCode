package twentyTwentyFour.dayFive

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val rules = mutableMapOf<Int, List<Int>>()
            val pageNumberUpdates = mutableListOf<List<Int>>()

            val parts = lines.joinToString("\n").split("\n\n")
            parts[0].lines().forEach { readFirstPart(it, rules) }
            parts[1].lines().forEach { readSecondPart(it, pageNumberUpdates) }

            var sum = 0
            pageNumberUpdates.forEach { row ->
                row.indices.forEach row@{ index ->
                    // None of the pages before should be in the list of pages that should be after the current page
                    if (row.subList(0, index).none { rules[row[index]]?.contains(it) == true }) return@row

                    val correctList = row.toMutableList()
                    // Correct the order of the list
                    while (correctList.indices.any { isOrderNotCorrect(correctList, it, rules) }) {
                        val indexOfWrongPlacedPage = correctList.indexOfFirst { isOrderNotCorrect(correctList, correctList.indexOf(it), rules) }
                        val newIndexOfWrongPlacedPage = row.indexOfFirst { rules[correctList[indexOfWrongPlacedPage]]?.contains(it) == true }
                        correctList.add(newIndexOfWrongPlacedPage, correctList.removeAt(indexOfWrongPlacedPage))
                    }
                    sum += correctList[row.size / 2]
                    return@forEach
                }
            }

            println("sum: $sum")
        }

        private fun isOrderNotCorrect(list: List<Int>, index: Int, rules: MutableMap<Int, List<Int>>): Boolean {
            return list.subList(0, index).any { rules[list[index]]?.contains(it) == true }
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
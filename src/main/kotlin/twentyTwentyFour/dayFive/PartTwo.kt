package twentyTwentyFour.dayFive

class PartTwo {
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
                row.indices.forEach row@{ index ->
                    // None of the pages before should be in the list of pages that should be after the current page
                    val isorderCorrect = row.subList(0, index).none { rules[row[index]]?.contains(it) == true }

                    if (isorderCorrect) return@row
                    val correctList = row.toMutableList()

                    while(correctList.indices.any { isOrderNotCorrect(correctList, it, rules) }) {
                        val indexOfWrongPlace = correctList.indexOfFirst { isOrderNotCorrect(correctList, correctList.indexOf(it), rules) }
                        val newIndexToPlace = row.indexOf(row.first { rules[correctList[indexOfWrongPlace]]?.contains(it) == true })
                        if(newIndexToPlace == -1) continue
                        val pageAtWrongPlace = correctList[indexOfWrongPlace]
                        correctList.removeAt(indexOfWrongPlace)
                        correctList.add(newIndexToPlace, pageAtWrongPlace)
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
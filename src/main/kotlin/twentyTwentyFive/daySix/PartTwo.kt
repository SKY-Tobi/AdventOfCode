package twentyTwentyFive.daySix

class PartTwo {
    companion object {
        const val MULTIPLY = "*"
        const val ADDITIONAL = "+"

        fun execute(lines: List<String>) {
            val (inputLines, lastLine) = lines.dropLast(1) to lines.last()

            val numbers = readInput(inputLines)
            val operations = lastLine.trim().split(Regex("\\s+")).map { if (it == MULTIPLY) MULTIPLY else ADDITIONAL }

            val results = operations.reversed().mapIndexed { index, operation ->
                when (operation) {
                    MULTIPLY -> evaluateNumbersForOperation(numbers[numbers.size - 1 - index]).reduce { acc, i -> acc * i }
                    ADDITIONAL -> evaluateNumbersForOperation(numbers[numbers.size - 1 - index]).reduce { acc, i -> acc + i }
                    else -> throw IllegalArgumentException("Unknown operation: $operation")
                }
            }.sum()

            println("sum of results: $results")
        }

        private fun evaluateNumbersForOperation(numbers: List<String>): List<Long> {
            val results = mutableListOf<String>()
            numbers.reversed().forEachIndexed { offset, number ->
                number.reversed().forEachIndexed { index, digit ->
                    val entry = results.getOrElse(index) {
                        val newEntry = ""
                        results.add(newEntry)
                        newEntry
                    }
                    results.removeAt(index)
                    results.add(index, digit + entry)
                }

            }
            return results.map { it.trim().toLong() }
        }

        private fun readInput(lines: List<String>): List<List<String>> {
            val numbersList = mutableListOf<ArrayList<String>>()
            val numberGroupSizes = mutableListOf<Int>()
            lines.forEach { line ->
                val numbers = Regex("\\d+").findAll(line).map { it.value.length }.toList()
                numbers.forEachIndexed { index, it ->
                    val entry = numberGroupSizes.getOrElse(index) {
                        val newEntry = 0
                        numberGroupSizes.add(newEntry)
                        newEntry
                    }
                    if (entry < it) {
                        numberGroupSizes.removeAt(index)
                        numberGroupSizes.add(index, it)
                    }
                }
            }

            lines.forEach { line ->
                val numbers = buildList {
                    var idx = 0
                    numberGroupSizes.forEach { size ->
                        // fill with spaces to ensure correct substring extraction (intellij removes trailing spaces on input file)
                        add(line.substring(idx, (idx + size).coerceAtMost(line.length)).padEnd(size, ' '))
                        idx += size + 1
                    }
                }
                numbers.forEachIndexed { index, it ->
                    val numbersListEntry = numbersList.getOrElse(index) {
                        val newList = ArrayList<String>()
                        numbersList.add(newList)
                        newList
                    }
                    numbersListEntry.add(it)
                }
            }
            return numbersList
        }
    }
}
package twentyTwentyFive.daySix

class PartOne {
    companion object {
        const val MULTIPLY = "*"
        const val ADDITIONAL = "+"

        fun execute(lines: List<String>) {
            val (inputLines, lastLine) = lines.dropLast(1) to lines.last()

            val numbers = readInput(inputLines)
            val operations = lastLine.trim().split(Regex("\\s+")).map { if(it == MULTIPLY) MULTIPLY else ADDITIONAL }

            val results = operations.mapIndexed { index, operation ->
                when (operation) {
                    MULTIPLY -> numbers[index].reduce { acc, i -> acc * i }
                    ADDITIONAL -> numbers[index].reduce { acc, i -> acc + i }
                    else -> throw IllegalArgumentException("Unknown operation: $operation")
                }
            }.sum()

            println("sum of results: $results")
        }

        private fun readInput(lines: List<String>): List<List<Long>> {
            val numbersList = mutableListOf<ArrayList<Long>>()
            lines.forEach { line ->
                val numbers = Regex("\\d+").findAll(line).map { it.value.toLong() }.toList()
                numbers.forEachIndexed { index, it ->
                    val numbersListEntry = numbersList.getOrElse(index) {
                        val newList = ArrayList<Long>()
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
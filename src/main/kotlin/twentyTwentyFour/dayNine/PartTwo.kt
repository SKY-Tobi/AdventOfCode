package twentyTwentyFour.dayNine

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var id = 0
            val result = mutableListOf<Pair<Int?, FieldOccupation>>()
            lines.flatMap { it.toCharArray().toList() }
                .windowed(2, 2, partialWindows = true)
                .forEach {
                    repeat(it[0].digitToInt()) {
                        result.add(Pair(id, FieldOccupation.OCCUPIED))
                    }
                    if (it.size > 1) {
                        for (i in 1..it[1].digitToInt()) {
                            result.add(Pair(null, FieldOccupation.EMPTY))
                        }
                    }
                    id++
                }

            // Pair<Int, Int> -> <id, amount>
            val groupsFromListReversed = ArrayDeque(
                result.filter { it.first != null }
                    .map { Pair(it.first!!, it.second) }
                    .groupBy { it.first }
                    .mapValues { it.value.size }
                    .toList()
                    .reversed()
            )

            groupsFromListReversed.forEach {
                fillEmptyFields(it, result)
            }

            val checksum = result.withIndex()
                .filter { it.value.second == FieldOccupation.OCCUPIED }
                .sumOf { it.index * it.value.first!! }
            println("Checksum: $checksum")
        }

        private fun fillEmptyFields(group: Pair<Int, Int>, result: MutableList<Pair<Int?, FieldOccupation>>) {
            val indexOfGroup = result.indexOfFirst { it.first == group.first }
            result.windowed(group.second, 1).forEachIndexed { resultIndex, window ->
                if(resultIndex >= indexOfGroup) {
                    return
                }
                if (window.all { it.second == FieldOccupation.EMPTY }) {
                    window.forEachIndexed { windowIndex, _ ->
                        result[resultIndex + windowIndex] = Pair(group.first, FieldOccupation.OCCUPIED)
                        result[indexOfGroup + windowIndex] = Pair(null, FieldOccupation.EMPTY)
                    }
                    return
                }
            }
        }
    }
}
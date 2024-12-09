package twentyTwentyFour.dayNine

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var id = 0
            val result = mutableListOf<Pair<Int?, FieldOccupation>>()
            lines.flatMap { it.toCharArray().toList() }
                .windowed(2, 2, partialWindows = true)
                .forEach {
                    for (i in 1..it[0].digitToInt()) {
                        result.add(Pair(id, FieldOccupation.OCCUPIED))
                    }
                    if (it.size > 1) {
                        for (i in 1..it[1].digitToInt()) {
                            result.add(Pair(null, FieldOccupation.EMPTY))
                        }
                    }
                    id++
            }
            val reversed = ArrayDeque(result.filter { it.first != null }.reversed())

            var counter = 0
            var emptyFieldsOnReverse = 0
            for ((index, it) in result.withIndex()) {
                if (index >= result.size - 1 - counter - emptyFieldsOnReverse) {
                    break
                }

                if (it.second == FieldOccupation.EMPTY) {
                    val next = reversed.removeFirst()
                    result[index] = Pair(next.first, FieldOccupation.OCCUPIED)
                    while(result[result.size - 1 - counter - emptyFieldsOnReverse].second == FieldOccupation.EMPTY) {
                        emptyFieldsOnReverse++
                    }
                    result[result.size - 1 - counter - emptyFieldsOnReverse] = Pair(null, FieldOccupation.EMPTY)
                    counter++
                }
            }

            var checksum = 0L
            result.filter { it.second == FieldOccupation.OCCUPIED }.forEachIndexed { index, it -> checksum += index * it.first!! }
            println("Checksum: $checksum")
        }
    }
}
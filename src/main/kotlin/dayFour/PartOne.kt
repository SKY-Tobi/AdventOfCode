package dayFour

private const val LOW_INDEX_FIRST = 0
private const val HIGH_INDEX_FIRST = 1
private const val LOW_INDEX_SECOND = 2
private const val HIGH_INDEX_SECOND = 3

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var occurrences = 0
            lines.stream().forEach { it ->
                val splitInput = it.split("[,\\-]".toRegex()).map { Integer.parseInt(it) }
                if (splitInput.size != 4) throw Exception("invalid amount of numbers in List")

                val indexOfLowestBoundaries = mutableListOf<Int>()
                if (splitInput[LOW_INDEX_FIRST] < splitInput[LOW_INDEX_SECOND]) {
                    indexOfLowestBoundaries.add(LOW_INDEX_FIRST)
                } else if (splitInput[LOW_INDEX_FIRST] > splitInput[LOW_INDEX_SECOND]) {
                    indexOfLowestBoundaries.add(LOW_INDEX_SECOND)
                } else {
                    // same number = both lowest
                    indexOfLowestBoundaries.add(LOW_INDEX_FIRST)
                    indexOfLowestBoundaries.add(LOW_INDEX_SECOND)
                }

                val indexOfHighestBoundaries = mutableListOf<Int>()
                if (splitInput[HIGH_INDEX_FIRST] > splitInput[HIGH_INDEX_SECOND]) {
                    indexOfHighestBoundaries.add(HIGH_INDEX_FIRST)
                } else if (splitInput[HIGH_INDEX_FIRST] < splitInput[HIGH_INDEX_SECOND]) {
                    indexOfHighestBoundaries.add(HIGH_INDEX_SECOND)
                } else {
                    // same number = both highest
                    indexOfHighestBoundaries.add(HIGH_INDEX_FIRST)
                    indexOfHighestBoundaries.add(HIGH_INDEX_SECOND)
                }

                if (indexOfLowestBoundaries.contains(LOW_INDEX_FIRST) && indexOfHighestBoundaries.contains(
                        HIGH_INDEX_FIRST
                    ) ||
                    indexOfLowestBoundaries.contains(LOW_INDEX_SECOND) && indexOfHighestBoundaries.contains(
                        HIGH_INDEX_SECOND
                    )
                ) {
                    occurrences++
                }
            }
            println("total occurences: $occurrences")
        }
    }
}
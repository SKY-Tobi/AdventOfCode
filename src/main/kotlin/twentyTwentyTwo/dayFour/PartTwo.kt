package twentyTwentyTwo.dayFour

private const val LOW_INDEX_FIRST = 0
private const val HIGH_INDEX_FIRST = 1
private const val LOW_INDEX_SECOND = 2
private const val HIGH_INDEX_SECOND = 3

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var occurrences = 0
            lines.forEach { it ->
                val splitInput = it.split("[,\\-]".toRegex()).map { Integer.parseInt(it) }
                if (splitInput.size != 4) throw Exception("invalid amount of numbers in List")

                // If the Highest index is higher or the same + the lowest index is smaller or the same as
                // the lowest index of the other, then they have an overlap occurrence
                if (splitInput[LOW_INDEX_FIRST] <= splitInput[LOW_INDEX_SECOND] && splitInput[HIGH_INDEX_FIRST] >= splitInput[LOW_INDEX_SECOND] ||
                    splitInput[LOW_INDEX_SECOND] <= splitInput[LOW_INDEX_FIRST] && splitInput[HIGH_INDEX_SECOND] >= splitInput[LOW_INDEX_FIRST]
                ) {
                    occurrences++
                }
            }
            println("total occurences: $occurrences")
        }
    }
}
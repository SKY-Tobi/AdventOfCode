package twentyTwentyFive.daySeven

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var splits = 0
            val beamsIndexes = mutableSetOf(lines.first().indexOf('S'))
            lines.drop(1).forEach { line ->
                // toSet (copy) to avoid concurrent modification exception
                beamsIndexes.toSet().forEach {
                    line[it].let { char ->
                        if (char == '^') {
                            splits++
                            beamsIndexes.add(it - 1)
                            beamsIndexes.add(it + 1)
                            beamsIndexes.remove(it)
                        }
                    }
                }
            }
            println("total splits: $splits")
        }
    }
}
package twentyTwentyFive.daySeven

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val beamsIndexes = mutableMapOf(lines.first().indexOf('S') to 1L)
            lines.drop(1).forEach { line ->
                // toMap (copy) to avoid concurrent modification exception
                beamsIndexes.toMap().forEach { (index, amount) ->
                    line[index].let { char ->
                        if (char == '^') {
                            beamsIndexes[index - 1] = beamsIndexes.getOrDefault(index - 1, 0L) + amount
                            beamsIndexes[index + 1] = beamsIndexes.getOrDefault(index + 1, 0L) + amount
                            beamsIndexes.remove(index, amount)
                        }
                    }
                }
            }
            val total = beamsIndexes.values.sum()
            println("total different timelines: $total")
        }
    }
}
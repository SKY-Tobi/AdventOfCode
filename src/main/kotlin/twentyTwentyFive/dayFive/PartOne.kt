package twentyTwentyFive.dayFive

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val groups: List<List<String>> = lines.split { it.isEmpty() }
            val freshIdRanges = readFreshIdRanges(groups[0])
            val idsToIterate = groups[1].map { it.toLong() }
            var totalFreshIds = 0
            idsToIterate.forEach {
                if (freshIdRanges.any { range -> it in range }) {
                    totalFreshIds++
                }
            }
            println("Total fresh IDs: $totalFreshIds")
        }

        private fun readFreshIdRanges(lines: List<String>): List<LongRange> {
            val ranges = ArrayList<LongRange>()
            lines.forEach {
                val parts = it.split("-")
                ranges.add(LongRange(parts[0].toLong(), parts[1].toLong()))
            }
            return ranges
        }

        private fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> {
            val result = mutableListOf<MutableList<T>>()
            var current = mutableListOf<T>()
            for (element in this) {
                if (predicate(element)) {
                    if (current.isNotEmpty()) {
                        result.add(current)
                        current = mutableListOf()
                    }
                } else {
                    current.add(element)
                }
            }
            if (current.isNotEmpty()) result.add(current)
            return result
        }
    }
}
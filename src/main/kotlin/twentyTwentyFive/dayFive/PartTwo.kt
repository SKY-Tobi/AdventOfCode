package twentyTwentyFive.dayFive

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val groups: List<List<String>> = lines.split { it.isEmpty() }
            val initialFreshIdRanges = readFreshIdRanges(groups[0]).sortedBy { it.first }
            val uniqueFreshIdRanges = mutableListOf<LongRange>()
            var totalFreshIds = 0L
            initialFreshIdRanges.forEach {
                val min = evaluateNotUsedMin(uniqueFreshIdRanges, it.start)
                val max = evaluateNotUsedMax(uniqueFreshIdRanges, it.endInclusive)
                if (min > max || min < it.start || max > it.endInclusive) return@forEach
                uniqueFreshIdRanges.add(LongRange(min, max))
            }
            uniqueFreshIdRanges.forEach {
                totalFreshIds += (it.endInclusive - it.start + 1)
            }
            println("Total fresh IDs: $totalFreshIds")
        }

        private fun evaluateNotUsedMin(uniqueFreshIdRanges: List<LongRange>, min: Long): Long {
            val overlappingRanges = uniqueFreshIdRanges.filter { range -> min in range }

            return if (overlappingRanges.isEmpty()) {
                min
            } else {
                evaluateNotUsedMin(uniqueFreshIdRanges, overlappingRanges.maxOf { it.endInclusive } + 1)
            }
        }

        private fun evaluateNotUsedMax(uniqueFreshIdRanges: List<LongRange>, max: Long): Long {
            val overlappingRanges = uniqueFreshIdRanges.filter { range -> max in range }

            return if (overlappingRanges.isEmpty()) {
                max
            } else {
                evaluateNotUsedMax(uniqueFreshIdRanges, overlappingRanges.minOf { it.start } - 1)
            }
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
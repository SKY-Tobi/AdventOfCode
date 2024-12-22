package twentyTwentyFour.dayNineteen

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val patterns = lines.takeWhile { it.isNotEmpty() }.flatMap { it.split(",").map(String::trim) }
            val designs = lines.dropWhile { it.isNotEmpty() }.drop(1)

            val memo = mutableMapOf<String, Long>()

            fun countWays(design: String): Long {
                if (design.isEmpty()) return 1
                if (design in memo) return memo[design]!!

                var totalWays = 0L
                for (pattern in patterns) {
                    if (design.startsWith(pattern)) {
                        totalWays += countWays(design.removePrefix(pattern))
                    }
                }
                memo[design] = totalWays
                return totalWays
            }

            val totalWays = designs.sumOf { countWays(it) }
            println("Total number of ways to construct all designs: $totalWays")
        }
    }
}
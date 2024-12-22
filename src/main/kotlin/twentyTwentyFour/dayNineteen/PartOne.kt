package twentyTwentyFour.dayNineteen

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val patterns = lines.takeWhile { it.isNotEmpty() }.flatMap { it.split(",").map(String::trim) }
            val designs = lines.dropWhile { it.isNotEmpty() }.drop(1)

            val memo = mutableMapOf<String, Boolean>()

            fun canConstruct(design: String): Boolean {
                if (design.isEmpty()) return true
                if (design in memo) return memo[design]!!

                for (pattern in patterns) {
                    if (design.startsWith(pattern) && canConstruct(design.removePrefix(pattern))) {
                        memo[design] = true
                        return true
                    }
                }
                memo[design] = false
                return false
            }

            val possibleDesignsCount = designs.count { canConstruct(it) }
            println("Number of possible designs: $possibleDesignsCount")
        }
    }
}
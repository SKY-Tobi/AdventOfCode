package twentyTwentyFive.dayFour

class PartTwo {
    companion object {
        const val PAPER_ROLL = '@'

        fun execute(lines: List<String>) {
            val maxWidth = lines[0].length
            val maxHeight = lines.size
            val lines = createMutableGrid(lines)
            var totalAccessiblePaperRolls = 0
            do {
                var isAPaperRollRemoved = false
                lines.forEachIndexed { y, row ->
                    row.forEachIndexed { x, cell ->
                        if (cell == PAPER_ROLL) {
                            var adjacentPaperRolls = 0

                            for (dy in -1..1) {
                                for (dx in -1..1) {
                                    if (dy == 0 && dx == 0) continue // skip self
                                    val newY = y + dy
                                    val newX = x + dx
                                    if (newY in 0 until maxWidth && newX in 0 until maxHeight) {
                                        if (lines[newY][newX] == PAPER_ROLL) {
                                            adjacentPaperRolls++
                                        }
                                    }
                                }
                            }

                            if (adjacentPaperRolls < 4) {
                                isAPaperRollRemoved = true
                                lines[y][x] = '.'
                                totalAccessiblePaperRolls++
                            }
                        }
                    }
                }
            } while (isAPaperRollRemoved)
            println("Total accessible paper rolls: $totalAccessiblePaperRolls")
        }

        fun createMutableGrid(lines: List<String>): Array<CharArray> {
            return Array(lines.size) { y -> lines[y].toCharArray() }
        }
    }
}
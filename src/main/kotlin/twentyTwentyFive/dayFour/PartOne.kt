package twentyTwentyFive.dayFour

class PartOne {
    companion object {
        const val PAPER_ROLL = '@'

        fun execute(lines: List<String>) {
            val maxWidth = lines[0].length
            val maxHeight = lines.size
            var totalAccessiblePaperRolls = 0
            lines.forEachIndexed { x, row ->
                row.forEachIndexed { y, cell ->
                    if (cell == PAPER_ROLL) {
                        var adjacentPaperRolls = 0

                        for (i in -1..1) {
                            for (j in -1..1) {
                                if (i == 0 && j == 0) continue // skip self
                                val newX = x + i
                                val newY = y + j
                                // check bounds
                                if (newX in 0 until maxWidth && newY in 0 until maxHeight) {
                                    if (lines[newX][newY] == PAPER_ROLL) {
                                        adjacentPaperRolls++
                                    }
                                }
                            }
                        }

                        if (adjacentPaperRolls < 4) {
                            totalAccessiblePaperRolls++
                        }
                    }
                }
            }
            println("Total accessible paper rolls: $totalAccessiblePaperRolls")
        }
    }
}
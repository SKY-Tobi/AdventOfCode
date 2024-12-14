package twentyTwentyFour.dayThirteen

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val fewestTokensToSpend = lines.chunked(4).sumOf { chunk ->
                calculateClawMachine(chunk.take(3))
            }
            println("The fewest number of tokens you can spend is $fewestTokensToSpend")
        }

        private fun calculateClawMachine(input: List<String>): Long {
            val buttonA = Regex("\\d+").findAll(input[0]).map { it.value.toInt() }.toList()
            val buttonB = Regex("\\d+").findAll(input[1]).map { it.value.toInt() }.toList()
            val prizeCoordinates = Regex("\\d+").findAll(input[2]).map { it.value.toLong() + 10000000000000 }.toList()

            // There is no fewest number of tokens you can spend, so you can just solve the linear equation
            // Cramer's rule
            fun pressButtons(aX: Int, aY: Int, bX: Int, bY: Int, prizeX: Long, prizeY: Long): Long {
                val det = aX * bY - aY * bX
                val a = (prizeX * bY - prizeY * bX) / det
                val b = (aX * prizeY - aY * prizeX) / det
                return if (aX * a + bX * b == prizeX && aY * a + bY * b == prizeY) {
                    a * 3 + b
                } else 0
            }

            return pressButtons(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prizeCoordinates[0], prizeCoordinates[1])
        }
    }
}
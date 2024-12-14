package twentyTwentyFour.dayThirteen

import kotlin.math.min

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val fewestTokensToSpend = lines.chunked(4).sumOf { chunk ->
                calculateClawMachine(chunk.take(3))
            }
            println("The fewest number of tokens you can spend is $fewestTokensToSpend")
        }

        private fun calculateClawMachine(input: List<String>): Int {
            val buttonA = Regex("\\d+").findAll(input[0]).map { it.value.toInt() }.toList()
            val buttonB = Regex("\\d+").findAll(input[1]).map { it.value.toInt() }.toList()
            val prizeCoordinates = Regex("\\d+").findAll(input[2]).map { it.value.toInt() }.toList()
            val possibleTokenSpends = mutableListOf<Int>()

            var buttonAHits = min(prizeCoordinates[0].div(buttonA[0]), prizeCoordinates[1].div(buttonA[1])) + 1
            while (buttonAHits-- >= 0) {
                if((prizeCoordinates[0] - buttonAHits * buttonA[0]) % buttonB[0] == 0 && (prizeCoordinates[1] - buttonAHits * buttonA[1]) % buttonB[1] == 0) {
                    val buttonBHitsX = (prizeCoordinates[0] - buttonAHits * buttonA[0]) / buttonB[0]
                    val buttonBHitsY = (prizeCoordinates[1] - buttonAHits * buttonA[1]) / buttonB[1]
                    if(buttonBHitsX == buttonBHitsY) possibleTokenSpends.add(3 * buttonAHits + buttonBHitsX)
                }
            }

            var buttonBHits = min(prizeCoordinates[0].div(buttonB[0]), prizeCoordinates[1].div(buttonB[1])) + 1
            while (buttonBHits-- >= 0) {
                if((prizeCoordinates[0] - buttonBHits * buttonB[0]) % buttonA[0] == 0 && (prizeCoordinates[1] - buttonBHits * buttonB[1]) % buttonA[1] == 0) {
                    val buttonAHitsX = (prizeCoordinates[0] - buttonBHits * buttonB[0]) / buttonA[0]
                    val buttonAHitsY = (prizeCoordinates[1] - buttonBHits * buttonB[1]) / buttonA[1]
                    if(buttonAHitsX == buttonAHitsY) possibleTokenSpends.add(3 * buttonAHitsX + buttonBHits)
                }
            }

            return possibleTokenSpends.minOrNull() ?: 0
        }
    }
}
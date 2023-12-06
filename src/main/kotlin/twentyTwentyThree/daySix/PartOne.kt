package twentyTwentyThree.daySix

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val races = readRaces(lines)
            val possibilitiesFound = mutableListOf<Int>()
            races.forEach {
                var possibilityFound = false
                var amountOfPossibilitiesFound = 0
                repeat(it.first) { millimetersPerSecond ->
                    if (millimetersPerSecond * (it.first - millimetersPerSecond) > it.second) {
                        possibilityFound = true
                        amountOfPossibilitiesFound++
                    } else if (possibilityFound) {
                        possibilitiesFound.add(amountOfPossibilitiesFound)
                        return@forEach // as after one point you only get slower, no need to check them anymore
                    }
                }
            }
            val product = possibilitiesFound.reduce { accumulator, element ->
                accumulator * element
            }

            println("number of ways to beat the record multiplied together: $product")
        }

        private fun readRaces(lines: List<String>): List<Pair<Int, Int>> {
            val race = mutableListOf<Pair<Int, Int>>()
            lines.forEachIndexed { indexCategory, it ->
                it.split(" ").filter { value -> value.toIntOrNull() != null }.forEachIndexed { indexRow, row ->
                    if (indexCategory == 0) {
                        race.add(indexRow, Pair(row.toInt(), 0))
                    } else {
                        race[indexRow] = Pair(race[indexRow].first, row.toInt())
                    }
                }
            }
            return race
        }
    }
}

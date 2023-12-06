package twentyTwentyThree.daySix

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val races = readRaces(lines)
            var amountOfPossibilitiesFound = 0L
            races.forEach {
                var possibilityFound = false
                repeat(it.first.toInt()) { millimetersPerSecond ->
                    if (millimetersPerSecond * (it.first - millimetersPerSecond) > it.second) {
                        possibilityFound = true
                        amountOfPossibilitiesFound++
                    } else if (possibilityFound) {
                        return@forEach // as after one point you only get slower, no need to check them anymore
                    }
                }
            }
            println("number of ways to beat the record: $amountOfPossibilitiesFound")
        }

        private fun readRaces(lines: List<String>): List<Pair<Long, Long>> {
            val race = mutableListOf<Pair<Long, Long>>()
            lines.forEachIndexed { indexCategory, it ->
                it.replace(" ", "").trim().split(":").filter { value -> value.toLongOrNull() != null }.forEachIndexed { indexRow, row ->
                    if (indexCategory == 0) {
                        race.add(indexRow, Pair(row.toLong(), 0))
                    } else {
                        race[indexRow] = Pair(race[indexRow].first, row.toLong())
                    }
                }
            }
            return race
        }
    }
}

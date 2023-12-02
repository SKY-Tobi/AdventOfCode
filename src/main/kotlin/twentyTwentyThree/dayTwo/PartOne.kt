package twentyTwentyThree.dayTwo

class PartOne {
    companion object {
        private val colors = arrayOf("red", "green", "blue")
        private const val MAX_RED_CUBES = 12
        private const val MAX_GREEN_CUBES = 13
        private const val MAX_BLUE_CUBES = 14
        fun execute(lines: List<String>) {
            // HashMap<GameRound - 1, HashMap<Color, HighestAmountShown>>()
            val scoreboard = HashMap<Int, HashMap<String, Int>>()

            lines.forEachIndexed { index, it ->
                val rounds = it.split(";")
                val stats = HashMap<String, Int>()
                val highestAmounts = HashMap<String, Int>()
                colors.forEach { color -> highestAmounts[color] = 0 }

                rounds.forEach {
                    colors.forEach { color ->
                        val regex = Regex("\\d+\\s$color")
                        val match = regex.find(it)

                        if (match != null) {
                            val currentAmountShown = Integer.parseInt(match.value.split(" ")[0])
                            if (currentAmountShown > highestAmounts[color]!!) {
                                highestAmounts[color] = currentAmountShown
                            }
                        }
                    }
                }

                highestAmounts.entries.forEach {
                    stats[it.key] = it.value
                }

                scoreboard[index] = stats
            }

            var sum = 0
            scoreboard.forEach Entries@ {
                it.value.entries.forEach { round ->
                    when (round.key) {
                        "red" -> {
                            if (round.value > MAX_RED_CUBES) return@Entries
                        }

                        "green" -> {
                            if (round.value > MAX_GREEN_CUBES) return@Entries
                        }

                        "blue" -> {
                            if (round.value > MAX_BLUE_CUBES) return@Entries
                        }
                    }
                }
                sum += it.key + 1 // index starts at 0 / round starts at 1
            }

            println("sum of the IDs: $sum")
        }
    }
}

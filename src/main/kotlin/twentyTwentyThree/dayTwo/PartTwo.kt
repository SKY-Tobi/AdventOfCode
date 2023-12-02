package twentyTwentyThree.dayTwo

class PartTwo {
    companion object {
        private val colors = arrayOf("red", "green", "blue")
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
            scoreboard.forEach {
                var power = 1
                it.value.entries.forEach { round ->
                    power *= round.value
                }
                sum += power
            }

            println("sum of the powers: $sum")
        }
    }
}

package twentyTwentyFour.dayEleven

class PartTwo {
    companion object {
        // Key is the stone and the amount of blinks left => Value is the amount of stones after the blinks
        private val cache = mutableMapOf<Pair<Long, Int>, Long>()
        fun execute(lines: List<String>) {
            val list = lines.flatMap { it.split(Regex("\\D+")) }.map { it.toLong() }.toMutableList()
            val stones = list.sumOf { calculateStones(it, 75) }
            println("Amount of Stones: $stones")
        }

        private fun calculateStones(stone: Long, blinksLeft: Int, key: Pair<Long, Int> = stone to blinksLeft): Long {
            return when {
                blinksLeft == 0 -> 1
                cache.containsKey(key) -> cache[key]!!
                else -> {
                    val amountOfStones = when {
                        stone == 0L -> calculateStones(1, blinksLeft - 1)
                        stone.toString().length % 2 == 0 -> {
                            val stoneString = stone.toString()
                            val halfLength = stoneString.length / 2
                            calculateStones(stoneString.substring(0, halfLength).toLong(), blinksLeft - 1) + calculateStones(stoneString.substring(halfLength).toLong(), blinksLeft - 1)
                        }
                        else -> calculateStones(stone * 2024, blinksLeft - 1)
                    }
                    cache[key] = amountOfStones
                    amountOfStones
                }
            }
        }
    }
}
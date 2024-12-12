package twentyTwentyFour.dayEleven

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val list = lines.flatMap { it.split(Regex("\\D+")) }.map { it.toLong() }.toMutableList()
            val amountOfBlinks = 25
            repeat(amountOfBlinks) {
                val size = list.size
                repeat(size) {
                    blink(list.removeFirst(), list)
                }
            }
            println("Amount of Stones: " + list.size)
        }

        private fun blink(stone: Long, list: MutableList<Long>) {
            if (stone == 0L) {
                list.add(1L)
            } else if (stone.toString().length % 2 == 0) {
                list.add(stone.toString().substring(0, stone.toString().length / 2).toLong())
                list.add(stone.toString().substring(stone.toString().length / 2).toLong())
            } else {
                list.add(stone * 2024)
            }
        }
    }
}
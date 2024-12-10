package twentyTwentyFour.dayTen

class PartOne {
    companion object {
        private const val FINISH_VALUE = 9
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val trailheads = findTrailheads(map)
            val sum = trailheads.sumOf {
                val run = trailhead(map, it.first, it.second, 0, 0)
                println("Run: $run")
                run
            }
            println("Sum: $sum")
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
        }

        private fun findTrailheads(map: Array<CharArray>): List<Pair<Int, Int>> {
            val trailheads = mutableListOf<Pair<Int, Int>>()
            for (y in map.indices) {
                for (x in map[y].indices) {
                    if (map[y][x] == '0') {
                        trailheads.add(Pair(x, y))
                    }
                }
            }
            return trailheads
        }

        private fun trailhead(map: Array<CharArray>, x: Int, y: Int, value: Int, counter: Int): Int {
            // TODO MARK VISITED 9
            var tempCounter = 0
            Direction.values().forEach {
                val (dx, dy) = it.getDeltas()
                if (isInsideMap(map, x + dx, y + dy) && map[y + dy][x + dx].digitToInt() == value + 1) {
                    if(value + 1 == FINISH_VALUE) {
                        tempCounter++
                    } else {
                        tempCounter += trailhead(map, x + dx, y + dy, value + 1, 0)
                    }
                }
            }
            return tempCounter
        }

        private fun isInsideMap(map: Array<CharArray>, x: Int, y: Int): Boolean {
            return y >= 0 && y < map.size && x >= 0 && x < map[y].size
        }
    }
}
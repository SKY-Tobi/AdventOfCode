package twentyTwentyFour.daySix

class PartOne {
    companion object {
        private const val START_CHARACTER = '^'
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val (x, y) = findStartingPoint(map)
            traverseMap(map, x, y).sumOf { row -> row.count { it == 'X' } }.also { println(it) }
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
        }

        private fun findStartingPoint(map: Array<CharArray>): Pair<Int, Int> {
            for (y in map.indices) {
                for (x in map[y].indices) {
                    if (map[y][x] == START_CHARACTER) {
                        return Pair(x, y)
                    }
                }
            }
            return Pair(-1, -1)
        }

        private fun traverseMap(map: Array<CharArray>, x: Int, y: Int): Array<CharArray> {
            var currentX = x
            var currentY = y
            var currentDirection = Direction.NORTH
            while (true) {
                val (dx, dy) = currentDirection.getDeltas()
                val nextX = currentX + dx
                val nextY = currentY + dy
                if (nextX < 0 || nextX >= map[0].size || nextY < 0 || nextY >= map.size) {
                    map[currentY][currentX] = 'X'
                    break
                }
                val nextCharacter = map[nextY][nextX]
                if (nextCharacter == '#') {
                    currentDirection = currentDirection.turnRight()
                    continue
                }
                map[currentY][currentX] = 'X'
                currentX = nextX
                currentY = nextY
            }
            return map
        }
    }
}
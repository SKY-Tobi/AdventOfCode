package twentyTwentyFour.daySix

class PartTwo {
    companion object {
        private const val START_CHARACTER = '^'
        private const val LOOP_BARRIER = 8450 // Map Size^2 / 2
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val (startX, startY) = findStartingPoint(map)
            traverseMap(map, startX, startY, Direction.NORTH, true)
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

        private fun traverseMap(map: Array<CharArray>, startX: Int, startY: Int, startingDirection: Direction, checkBoxPlacement: Boolean): Boolean {
            var currentX = startX
            var currentY = startY
            var currentDirection = startingDirection
            var boxCounter = 0
            var stepCounter = 0
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

                if(checkBoxPlacement) {
                    if (!(nextY == startY && nextX == startX) && nextCharacter != 'X' && canBoxBePlaced(map, currentX, currentY, nextX, nextY, currentDirection)) {
                        boxCounter++
                    }
                } else {
                    if(nextY == startY && nextX == startX && currentDirection == startingDirection || ++stepCounter > LOOP_BARRIER) {
                        return true
                    }
                }

                currentX = nextX
                currentY = nextY
            }
            if(checkBoxPlacement) println("Boxes placed: $boxCounter")
            return false
        }

        private fun canBoxBePlaced(
            map: Array<CharArray>,
            currentX: Int,
            currentY: Int,
            nextX: Int,
            nextY: Int,
            currentDirection: Direction
        ): Boolean {
            map[nextY][nextX] = '#'
            val result = traverseMap(map.map { it.clone() }.toTypedArray(), currentX, currentY, currentDirection, false)
            map[nextY][nextX] = '.'
            return result
        }
    }
}
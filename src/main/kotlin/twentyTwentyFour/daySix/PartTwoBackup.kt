package twentyTwentyFour.daySix

class PartTwoBackup {
    companion object {
        private const val START_CHARACTER = '^'
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val (x, y) = findStartingPoint(map)
            val traversedMap = calculateTraversedMap(map, x, y)
            var counter: Int
            traverseMap(initializeMap(lines), x, y, traversedMap).also { counter = it }
            println("Boxes placed: $counter")
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

        /*private fun traverseMap(map: Array<CharArray>, x: Int, y: Int){
            val traversedMap = mutableMapOf<Pair<Int, Int>, MutableList<Direction>>()
            var currentX = x
            var currentY = y
            var currentDirection = Direction.NORTH
            var boxCounter = 0
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
                    traversedMap.getOrPut(Pair(currentY, currentX)) { mutableListOf() }.add(currentDirection)
                    currentDirection = currentDirection.turnRight()
                    continue
                }
                map[currentY][currentX] = 'X'
                traversedMap.getOrPut(Pair(currentY, currentX)) { mutableListOf() }.add(currentDirection)

                if (!(nextY == y && nextX == x) && canBoxBePlaced(map, currentX, currentY, currentDirection, traversedMap)) {
                    boxCounter++
                }

                currentX = nextX
                currentY = nextY
            }
            println("Boxes placed: $boxCounter")
        }*/

        private fun calculateTraversedMap(map: Array<CharArray>, x: Int, y: Int): MutableMap<Pair<Int, Int>, MutableList<Direction>> {
            val traversedMap = mutableMapOf<Pair<Int, Int>, MutableList<Direction>>()
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
                    traversedMap.getOrPut(Pair(currentY, currentX)) { mutableListOf() }.add(currentDirection)
                    currentDirection = currentDirection.turnRight()
                    continue
                }
                map[currentY][currentX] = 'X'
                traversedMap.getOrPut(Pair(currentY, currentX)) { mutableListOf() }.add(currentDirection)
                currentX = nextX
                currentY = nextY
            }
            return traversedMap
        }

        private fun traverseMap(map: Array<CharArray>, x: Int, y: Int, traversedMap: MutableMap<Pair<Int, Int>, MutableList<Direction>>): Int {
            var currentX = x
            var currentY = y
            var currentDirection = Direction.NORTH
            var counter = 0
            while (true) {
                val (dx, dy) = currentDirection.getDeltas()
                val nextX = currentX + dx
                val nextY = currentY + dy
                if (nextX < 0 || nextX >= map[0].size || nextY < 0 || nextY >= map.size) {
                    break
                }
                val nextCharacter = map[nextY][nextX]
                if (nextCharacter == '#') {
                    currentDirection = currentDirection.turnRight()
                    continue
                }
                if(canBoxBePlaced(map, nextX, nextY, currentDirection, traversedMap)){
                    println("Box placed at: $nextX, $nextY")
                    counter++
                }
                currentX = nextX
                currentY = nextY
            }
            return counter
        }

        private fun canBoxBePlaced(
            map: Array<CharArray>,
            currentX: Int,
            currentY: Int,
            currentDirection: Direction,
            traversedMap: MutableMap<Pair<Int, Int>, MutableList<Direction>>
        ): Boolean {
            val (dx, dy) = currentDirection.turnRight().getDeltas()
            var rightX = currentX
            var rightY = currentY
            var alreadyTraversedInDirection = false
            while (rightX > 0 && rightX < map[0].size - 1 && rightY > 0 && rightY < map.size - 1) {
                rightX += dx
                rightY += dy
                if (map[rightY][rightX] == 'X' && traversedMap.getOrDefault(Pair(rightY, rightX), mutableListOf()).contains(currentDirection.turnRight())) {
                    alreadyTraversedInDirection = true
                }
                if (map[rightY][rightX] == '#') {
                    return alreadyTraversedInDirection
                }
            }
            return false
        }
    }
}
package twentyTwentyFour.dayFifteen

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val parts = lines.joinToString("\n").split("\n\n")
            val map = MutableList(parts[0].lines().size) { MutableList(lines[0].length) { '.' } }
            val sequence = mutableListOf<Direction>()
            parts[0].lines().forEachIndexed { indexY, s -> initializeMap(s, indexY, map) }
            initializeSequence(parts[1], sequence)

            var robotPosition = getPositionOfRobot(map)
            sequence.forEach {
                robotPosition = traverse(map, robotPosition, it)
            }

            val sum = map.mapIndexed { indexY, row ->
                row.mapIndexed { indexX, it ->
                    if (it == 'O') {
                        100 * indexY + indexX
                    } else 0
                }.sum()
            }.sum()

            println("sum of all boxes' GPS coordinates: $sum")
        }

        private fun initializeMap(s: String, indexY: Int, map: MutableList<MutableList<Char>>) {
            s.toCharArray().forEachIndexed { indexX, char -> map[indexY][indexX] = (char) }
        }

        private fun initializeSequence(it: String, sequence: MutableList<Direction>) {
            it.lines().flatMap { it.toCharArray().asIterable() }.forEach { sequence.add(it.toDirection()) }
        }

        private fun getPositionOfRobot(map: MutableList<MutableList<Char>>): Pair<Int, Int> {
            map.forEachIndexed { y, list ->
                list.forEachIndexed { x, char ->
                    if (char == '@') {
                        return Pair(x, y)
                    }
                }
            }
            throw IllegalArgumentException("Robot not found")
        }

        private fun traverse(
            map: MutableList<MutableList<Char>>,
            robotPosition: Pair<Int, Int>,
            direction: Direction
        ): Pair<Int, Int> {
            val (dx, dy) = direction.getDeltas()
            var newX = robotPosition.first + dx
            var newY = robotPosition.second + dy
            if (map[newY][newX] == '.') {
                map[robotPosition.second][robotPosition.first] = '.'
                map[robotPosition.second + dy][robotPosition.first + dx] = '@'
                return Pair(newX, newY)
            }
            if (map[newY][newX] == '#') {
                return robotPosition
            }

            while(map[newY][newX] != '.') {
                if (map[newY][newX] == '#') {
                    return robotPosition
                }
                newX += dx
                newY += dy
            }

            map[robotPosition.second][robotPosition.first] = '.'
            map[robotPosition.second + dy][robotPosition.first + dx] = '@'
            map[newY][newX] = 'O'

            return Pair(robotPosition.first + dx, robotPosition.second + dy)
        }

        private fun Char.toDirection(): Direction {
            return when (this) {
                '^' -> Direction.NORTH
                '>' -> Direction.EAST
                'v' -> Direction.SOUTH
                '<' -> Direction.WEST
                else -> throw IllegalArgumentException("Invalid direction")
            }
        }
    }
}
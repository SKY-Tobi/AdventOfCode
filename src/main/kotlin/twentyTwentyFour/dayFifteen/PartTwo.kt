package twentyTwentyFour.dayFifteen

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val parts = lines.joinToString("\n").split("\n\n")
            val map = MutableList(parts[0].lines().size) { MutableList(lines[0].length * 2) { '.' } }
            val sequence = parts[1].lines().flatMap { it.toCharArray().asIterable() }.map { it.toDirection() }
            val boxes = mutableListOf<Box>()
            parts[0].lines().forEachIndexed { indexY, s -> initializeMap(s, indexY, map, boxes) }

            var robotPosition = getPositionOfRobot(map)
            sequence.forEach { robotPosition = traverse(map, boxes, robotPosition, it) }

            val sum = map.mapIndexed { indexY, row ->
                row.mapIndexed { indexX, it ->
                    if (it == '[') {
                        100 * indexY + indexX
                    } else 0
                }.sum()
            }.sum()

            println("sum of all boxes' GPS coordinates: $sum")
        }

        private fun initializeMap(s: String, indexY: Int, map: MutableList<MutableList<Char>>, boxes: MutableList<Box>) {
            s.toCharArray().forEachIndexed { indexX, char ->
                val realIndexX = indexX * 2
                when (char) {
                    'O' -> {
                        map[indexY][realIndexX] = '['
                        map[indexY][realIndexX + 1] = ']'
                        boxes.add(Box(Pair(realIndexX, indexY), Pair(realIndexX + 1, indexY)))
                    }
                    '@' -> {
                        map[indexY][realIndexX] = char
                        map[indexY][realIndexX + 1] = '.'
                    }
                    else -> {
                        map[indexY][realIndexX] = char
                        map[indexY][realIndexX + 1] = char
                    }
                }
            }
        }

        private fun getPositionOfRobot(map: MutableList<MutableList<Char>>): Pair<Int, Int> {
            map.forEachIndexed { y, list ->
                list.forEachIndexed { x, char ->
                    if (char == '@') return Pair(x, y)
                }
            }
            throw IllegalArgumentException("Robot not found")
        }

        private fun traverse(map: MutableList<MutableList<Char>>, boxes: MutableList<Box>, robotPosition: Pair<Int, Int>, direction: Direction): Pair<Int, Int> {
            val (dx, dy) = direction.getDeltas()
            val newX = robotPosition.first + dx
            val newY = robotPosition.second + dy

            return when {
                map[newY][newX] == '.' -> {
                    map[robotPosition.second][robotPosition.first] = '.'
                    map[newY][newX] = '@'
                    Pair(newX, newY)
                }
                map[newY][newX] == '#' -> robotPosition
                boxes.any { it.contains(Pair(newX, newY)) } -> {
                    val boxHit = boxes.first { it.contains(Pair(newX, newY)) }
                    if (boxHit.canBoxBePushed(direction, map, boxes)) {
                        boxHit.moveBox(direction, map, boxes)
                        map[robotPosition.second][robotPosition.first] = '.'
                        map[newY][newX] = '@'
                        Pair(newX, newY)
                    } else robotPosition
                }
                else -> robotPosition
            }
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
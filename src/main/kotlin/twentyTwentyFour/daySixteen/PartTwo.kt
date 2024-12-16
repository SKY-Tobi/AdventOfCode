package twentyTwentyFour.daySixteen

import java.util.*

class PartTwo {
    companion object {
        private val START_DIRECTION = Direction.EAST
        private const val ROTATION_PENALTY = 1000
        fun execute(lines: List<String>) {
            val (map, startPoint, endPoint) = parseMap(lines)

            val finishPoints = mutableListOf<Int>()
            traverseMap(finishPoints, map, startPoint, endPoint)
            println(finishPoints.minOrNull())
        }

        private fun parseMap(lines: List<String>): Triple<MutableList<MutableList<Char>>, Pair<Int, Int>, Pair<Int, Int>> {
            val map = MutableList(lines.size) { MutableList(lines[0].length) { '.' } }
            var startPoint: Pair<Int, Int>? = null
            var endPoint: Pair<Int, Int>? = null

            for (y in lines.indices) {
                val line = lines[y]
                for (x in line.indices) {
                    map[y][x] = line[x]
                    when (line[x]) {
                        'S' -> startPoint = Pair(x, y)
                        'E' -> endPoint = Pair(x, y)
                    }
                }
            }

            return Triple(map, startPoint!!, endPoint!!)
        }

        private fun traverseMap(
            finishPoints: MutableList<Int>,
            map: MutableList<MutableList<Char>>,
            startPoint: Pair<Int, Int>,
            endPoint: Pair<Int, Int>
        ) {
            val pq = PriorityQueue<Triple<Pair<Int, Int>, Int, Direction>>(compareBy { it.second })
            val visited = mutableSetOf<Pair<Int, Int>>()
            pq.add(Triple(startPoint, 1, START_DIRECTION))

            while (pq.isNotEmpty()) {
                val (currentPosition, currentPoints, currentDirection) = pq.poll()
                if (currentPosition in visited) continue
                visited.add(currentPosition)

                for ((newDirection, delta, rotations) in currentDirection.getPossibleDirections()) {
                    val (dx, dy) = delta
                    val (x, y) = currentPosition
                    val newX = x + dx
                    val newY = y + dy
                    val newPoints = currentPoints + 1 + rotations * ROTATION_PENALTY

                    if (Pair(newX, newY) == endPoint) {
                        finishPoints.add(currentPoints)
                        continue
                    }

                    if (newX in map[0].indices && newY in map.indices && map[newY][newX] == '.') {
                        pq.add(Triple(Pair(newX, newY), newPoints, newDirection))
                    }
                }
            }
        }
    }
}
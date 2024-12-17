package twentyTwentyFour.daySixteen

import java.util.*

class PartTwo {
    companion object {
        private val START_DIRECTION = Direction.EAST
        private const val ROTATION_PENALTY = 1000
        fun execute(lines: List<String>) {
            val (map, startPoint, endPoint) = parseMap(lines)
            println(traverseMap(map, startPoint, endPoint))
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
            map: MutableList<MutableList<Char>>,
            startPoint: Pair<Int, Int>,
            endPoint: Pair<Int, Int>
        ): Int {
            val finishPoints = mutableListOf<Int>()
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

            val correctPaths = mutableSetOf<Pair<Int, Int>>()

            fun backTrace(
                map: MutableList<MutableList<Char>>,
                currentPosition: Pair<Int, Int>,
                currentDirection: Direction,
                currentPoints: Int,
                path: MutableList<Pair<Int, Int>>,
                maxScore: Int
            ) {
                for ((newDirection, delta, rotations) in currentDirection.getPossibleDirections()) {
                    val (dx, dy) = delta
                    val (x, y) = currentPosition
                    val newX = x + dx
                    val newY = y + dy
                    var newPoints =
                        currentPoints + 1 + if (currentPosition != endPoint) rotations * ROTATION_PENALTY else 0

                    if (newPoints > maxScore) {
                        continue
                    }

                    if (Pair(newX, newY) == startPoint) {
                        if (currentDirection == Direction.SOUTH) {
                            newPoints += ROTATION_PENALTY
                        }
                        if (newPoints <= maxScore) {
                            correctPaths.addAll(path)
                        }
                    } else if (newX in map[0].indices && newY in map.indices && map[newY][newX] == '.') {
                        path.add(Pair(newX, newY))
                        backTrace(map, Pair(newX, newY), newDirection, newPoints, path, maxScore)
                        path.removeAt(path.size - 1)
                    }
                }
            }

            backTrace(map, endPoint, Direction.WEST, 0, mutableListOf(), finishPoints.minOrNull()!!)

            return 2 + correctPaths.size
        }
    }
}
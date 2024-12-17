package twentyTwentyFour.daySixteen

import java.util.*
import kotlin.collections.HashSet

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
            val lowestScore = getLowestScore(startPoint, START_DIRECTION, endPoint, map)
            return getAllShortestPathLengths(startPoint, endPoint, lowestScore, map)
        }

        private fun getLowestScore(
            startPoint: Pair<Int, Int>,
            startDirection: Direction,
            endPoint: Pair<Int, Int>,
            map: MutableList<MutableList<Char>>
        ): Long {
            val visited = HashSet<Triple<Int, Int, Direction>>()
            val scores = HashMap<Triple<Int, Int, Direction>, Long>()
            val queue = PriorityQueue<Pair<Triple<Int, Int, Direction>, Long>>(compareBy { it.second })

            queue.add(Triple(startPoint.first, startPoint.second, startDirection) to 0L)
            while (queue.isNotEmpty()) {
                val (current, score) = queue.poll()
                if (Pair(current.first, current.second) == endPoint) return score

                if (visited.contains(current)) continue
                visited.add(current)

                val nextMoves = current.third.getPossibleDirections().map { (newDirection, delta, rotations) ->
                    val (dx, dy) = delta
                    val newX = current.first + dx
                    val newY = current.second + dy
                    Triple(Pair(newX, newY), newDirection, score + 1 + rotations * ROTATION_PENALTY)
                }

                for ((nextPosition, nextDirection, nextScore) in nextMoves) {
                    if (map[nextPosition.second][nextPosition.first] == '#' ||
                        visited.contains(Triple(nextPosition.first, nextPosition.second, nextDirection))
                    ) continue

                    if (nextScore < scores.getOrDefault(Triple(nextPosition.first, nextPosition.second, nextDirection),
                            Long.MAX_VALUE)) {
                        scores[Triple(nextPosition.first, nextPosition.second, nextDirection)] = nextScore
                        queue.add(Triple(nextPosition.first, nextPosition.second, nextDirection) to nextScore)
                    }
                }
            }
            return Long.MAX_VALUE - Int.MAX_VALUE
        }

        private fun getAllShortestPathLengths(
            startPoint: Pair<Int, Int>,
            endPoint: Pair<Int, Int>,
            targetScore: Long,
            map: MutableList<MutableList<Char>>
        ): Int {
            val visited = HashSet<Triple<Int, Int, Direction>>()
            val queue = ArrayDeque<Triple<Pair<Int, Int>, Direction, Long>>()
            val validPositions = HashSet<Pair<Int, Int>>()

            queue.add(Triple(startPoint, Direction.EAST, 0L))
            while (queue.isNotEmpty()) {
                val (currentPosition, currentDirection, currentScore) = queue.removeFirst()
                validPositions.add(currentPosition)

                if (currentPosition == endPoint) continue

                if (visited.contains(Triple(currentPosition.first, currentPosition.second, currentDirection))) continue
                visited.add(Triple(currentPosition.first, currentPosition.second, currentDirection))

                val nextMoves = currentDirection.getPossibleDirections().map { (newDirection, delta, rotations) ->
                    val (dx, dy) = delta
                    val newX = currentPosition.first + dx
                    val newY = currentPosition.second + dy
                    Triple(Pair(newX, newY), newDirection, 1 + rotations * ROTATION_PENALTY)
                }

                for (nextPosition in nextMoves) {
                    if (map[nextPosition.first.second][nextPosition.first.first] == '#' || visited.contains(
                            Triple(
                                nextPosition.first.first,
                                nextPosition.first.second,
                                nextPosition.second
                            )
                        )
                    ) continue
                    if (currentScore + nextPosition.third + getLowestScore(
                            nextPosition.first,
                            nextPosition.second,
                            endPoint,
                            map
                        ) > targetScore
                    ) continue

                    val nextScore = currentScore + nextPosition.third
                    queue.add(Triple(nextPosition.first, nextPosition.second, nextScore))
                }
            }

            return validPositions.count()
        }
    }
}
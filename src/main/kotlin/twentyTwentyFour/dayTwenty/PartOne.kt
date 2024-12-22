package twentyTwentyFour.dayTwenty

import java.util.*

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val map = lines.map { it.toCharArray() }
            val start = findPoint(map, 'S')
            val end = findPoint(map, 'E')

            val minStepsWithoutCheat = aStar(map, start, end)
            val allCheatPaths = findAllCheatPaths(map, start, end)
                .filter { it < minStepsWithoutCheat }
                .sortedBy { minStepsWithoutCheat - it }

            val cheatGroups = allCheatPaths.groupingBy { it }.eachCount()
            cheatGroups.forEach { (timeSaved, count) ->
                println("There ${if (count > 1) "are" else "is"} $count cheat${if (count > 1) "s" else ""} that save${if (count > 1) "" else "s"} ${minStepsWithoutCheat - timeSaved} picosecond${if (timeSaved > 1) "s" else ""}.")
            }

            println("There are ${cheatGroups.filter { minStepsWithoutCheat - it.key >= 100 }.values.sum()} cheats that save you at least 100 picoseconds.")

        }

        private fun findPoint(map: List<CharArray>, char: Char): Pair<Int, Int> {
            for (y in map.indices) {
                for (x in map[y].indices) {
                    if (map[y][x] == char) {
                        return Pair(x, y)
                    }
                }
            }
            throw IllegalArgumentException("Point $char not found in the map")
        }

        private fun aStar(map: List<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
            val openSet = PriorityQueue(compareBy<State> { it.steps + heuristic(it.point, end) })
            val gScore = mutableMapOf<Pair<Int, Int>, Int>().withDefault { Int.MAX_VALUE }
            gScore[start] = 0
            openSet.add(State(start, 0, false))

            while (openSet.isNotEmpty()) {
                val current = openSet.poll()
                if (current.point == end) {
                    return current.steps
                }

                for (direction in Direction.values()) {
                    val (dx, dy) = direction.getDeltas()
                    val nextPoint = Pair(current.point.first + dx, current.point.second + dy)
                    if (isValid(map, nextPoint)) {
                        val tentativeGScore = gScore.getValue(current.point) + 1
                        if (tentativeGScore < gScore.getValue(nextPoint)) {
                            gScore[nextPoint] = tentativeGScore
                            openSet.add(State(nextPoint, tentativeGScore, current.cheated))
                        }
                    }
                }
            }
            return Int.MAX_VALUE
        }

        private fun heuristic(point: Pair<Int, Int>, end: Pair<Int, Int>): Int {
            return Math.abs(point.first - end.first) + Math.abs(point.second - end.second)
        }

        private fun findAllCheatPaths(map: List<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): List<Int> {
            val queue: Queue<State> = LinkedList()
            val visited = mutableMapOf<Pair<Int, Int>, Int>()
            val breachedWallsWithCheat = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
            val cheatPaths = mutableListOf<Int>()
            queue.add(State(start, 0, false))

            while (queue.isNotEmpty()) {
                val current = queue.poll()

                for (direction in Direction.values()) {
                    val (dx, dy) = direction.getDeltas()
                    val nextPoint = Pair(current.point.first + dx, current.point.second + dy)
                    if (isValid(map, nextPoint)) {
                        val nextState = State(nextPoint, current.steps + 1, false)
                        if (nextState.steps < visited.getOrDefault(nextState.point, Int.MAX_VALUE)) {
                            visited[nextState.point] = nextState.steps
                            queue.add(nextState)
                        }
                    } else if (!current.cheated) {
                        val cheatPoint = Pair(current.point.first + dx * 2, current.point.second + dy * 2)
                        if (isValid(map, cheatPoint) && !breachedWallsWithCheat.contains(Pair(nextPoint, cheatPoint))) {
                            val cheatSteps = aStar(map, cheatPoint, end)
                            if (cheatSteps != Int.MAX_VALUE) {
                                cheatPaths.add(cheatSteps + current.steps + 2)
                                breachedWallsWithCheat.add(Pair(nextPoint, cheatPoint))
                            }
                        }
                    }
                }
            }
            return cheatPaths
        }

        private fun isValid(map: List<CharArray>, point: Pair<Int, Int>): Boolean {
            return point.second in map.indices && point.first in map[point.second].indices && map[point.second][point.first] != '#'
        }
    }
}
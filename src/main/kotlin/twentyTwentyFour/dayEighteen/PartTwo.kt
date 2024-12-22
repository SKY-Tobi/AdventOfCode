package twentyTwentyFour.dayEighteen

import java.util.*

class PartTwo {
    companion object {
        private const val HEIGHT = 70
        private const val WIDTH = 70
        fun execute(lines: List<String>) {
            val input = lines.map { it.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
            val grid = Array(HEIGHT + 1) { CharArray(WIDTH + 1) { '.' } }

            fun isPathAvailable(): Boolean {
                val queue: Queue<Pair<Int, Int>> = LinkedList()
                queue.add(0 to 0)
                val visited = Array(HEIGHT + 1) { BooleanArray(WIDTH + 1) { false } }
                visited[0][0] = true

                while (queue.isNotEmpty()) {
                    val (x, y) = queue.poll()
                    if (x == WIDTH && y == HEIGHT) return true

                    for (direction in Direction.values()) {
                        val (dx, dy) = direction.getDeltas()
                        val (nextX, nextY) = x + dx to y + dy
                        if (nextX in 0..WIDTH && nextY in 0..HEIGHT && !visited[nextY][nextX] && grid[nextY][nextX] == '.') {
                            visited[nextY][nextX] = true
                            queue.add(nextX to nextY)
                        }
                    }
                }
                return false
            }

            for ((x, y) in input) {
                grid[y][x] = '#'
                if (!isPathAvailable()) {
                    println("Byte: '$x,$y' prevents the exit from being reachable")
                    return
                }
            }

            println("No byte prevents the exit from being reachable")
        }
    }
}
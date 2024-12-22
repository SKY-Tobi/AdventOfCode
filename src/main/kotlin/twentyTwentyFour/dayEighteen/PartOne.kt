package twentyTwentyFour.dayEighteen

import java.util.*

class PartOne {
    companion object {
        private const val HEIGHT = 70
        private const val WIDTH = 70
        fun execute(lines: List<String>) {
            val input = lines.take(1024).map { it.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
            val grid = Array(HEIGHT + 1) { CharArray(WIDTH + 1) { '.' } }

            for ((x, y) in input) {
                grid[y][x] = '#'
            }

            val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
            queue.add(Triple(0, 0, 0))
            val visited = Array(HEIGHT + 1) { BooleanArray(WIDTH + 1) { false } }
            visited[0][0] = true

            while (queue.isNotEmpty()) {
                val (x, y, steps) = queue.poll()
                if (x == WIDTH && y == HEIGHT) {
                    println("Minimum steps: $steps")
                    return
                }

                for (direction in Direction.values()) {
                    val (dx, dy) = direction.getDeltas()
                    val (nextX, nextY) = x + dx to y + dy
                    if (nextX in 0..WIDTH && nextY in 0..HEIGHT && !visited[nextY][nextX] && grid[nextY][nextX] == '.') {
                        visited[nextY][nextX] = true
                        queue.add(Triple(nextX, nextY, steps + 1))
                    }
                }
            }

            println("No path found")
        }
    }
}
package twentyTwentyFour.dayFourteen

class PartTwo {
    companion object {
        private const val WIDE = 101
        private const val TALL = 103
        private const val SECONDS = 10000
        fun execute(lines: List<String>) {
            var map = MutableList(TALL) { MutableList(WIDE) { '.' } }
            val robots = lines.map { robot ->
                val (px, py, vx, vy) = Regex("-?\\d+").findAll(robot).map { it.value.toInt() }.toList()
                Robot(Pair(px, py), Pair(vx, vy))
            }
            run repeatBlock@{
                repeat(SECONDS) {
                    robots.forEach { robot -> robot.position = calculateNewPositionWithTeleport(robot) }
                    if (countRobotsInUniqueFields(robots) == robots.size) {
                        println("Seconds lapsed: ${it + 1}")
                        robots.forEach { robot ->
                            map[robot.position!!.second][robot.position!!.first] = '#'
                        }
                        map.forEach { row ->
                            println(row.joinToString(""))
                        }
                        map = MutableList(TALL) { MutableList(WIDE) { '.' } }
                        return@repeatBlock
                    }
                }
            }
        }
        private fun calculateNewPositionWithTeleport(robot: Robot): Pair<Int, Int> {
            val x = (robot.position!!.first + robot.velocity!!.first).mod(WIDE)
            val y = (robot.position!!.second + robot.velocity!!.second).mod(TALL)
            return Pair(x, y)
        }

        private fun countRobotsInUniqueFields(robots: List<Robot>): Int = robots.map { it.position!! }.toSet().size
    }
}
package twentyTwentyFour.dayFourteen

class PartOne {
    companion object {
        private const val WIDE = 101
        private const val TALL = 103
        private const val SECONDS = 100
        fun execute(lines: List<String>) {
            val map = MutableList(TALL) { MutableList(WIDE) { '.' } }
            val robots = lines.map { robot ->
                val (px, py, vx, vy) = Regex("-?\\d+").findAll(robot).map { it.value.toInt() }.toList()
                Robot(Pair(px, py), Pair(vx, vy))
            }
            repeat(SECONDS) {
                robots.forEach { robot ->
                    if(robot.position!!.first + robot.velocity!!.first in map[0].indices && robot.position!!.second + robot.velocity!!.second in map.indices) {
                        robot.position = Pair(robot.position!!.first + robot.velocity!!.first, robot.position!!.second + robot.velocity!!.second)
                    } else {
                        robot.position = calculateNewPositionWithTeleport(robot, map)
                    }
                }
            }
            val (q1, q2, q3, q4) = countRobotsInQuadrants(robots)
            println("Safety factor is ${q1 * q2 * q3 * q4}")
        }

        private fun calculateNewPositionWithTeleport(robot: Robot, map: MutableList<MutableList<Char>>): Pair<Int, Int> {
            val x = if(robot.position!!.first + robot.velocity!!.first !in map[0].indices) {
                if(robot.position!!.first + robot.velocity!!.first < 0) {
                    WIDE + robot.position!!.first + robot.velocity!!.first
                } else {
                    robot.position!!.first + robot.velocity!!.first - WIDE
                }
            } else {
                robot.position!!.first + robot.velocity!!.first
            }
            val y = if(robot.position!!.second + robot.velocity!!.second !in map.indices) {
                if(robot.position!!.second + robot.velocity!!.second < 0) {
                    TALL + robot.position!!.second + robot.velocity!!.second
                } else {
                    robot.position!!.second + robot.velocity!!.second - TALL
                }
            } else {
                robot.position!!.second + robot.velocity!!.second
            }
            return Pair(x, y)
        }

        private fun countRobotsInQuadrants(robots: List<Robot>): List<Int> {
            val borderLineX = WIDE / 2
            val borderLineY = TALL / 2
            var q1 = 0 // TOP RIGHT
            var q2 = 0 // TOP LEFT
            var q3 = 0 // BOTTOM LEFT
            var q4 = 0 // BOTTOM RIGHT
            robots.forEach { robot ->
                if(robot.position!!.first > borderLineX && robot.position!!.second < borderLineY) {
                    q1++
                } else if(robot.position!!.first < borderLineX && robot.position!!.second < borderLineY) {
                    q2++
                } else if(robot.position!!.first < borderLineX && robot.position!!.second > borderLineY) {
                    q3++
                } else if(robot.position!!.first > borderLineX && robot.position!!.second > borderLineY) {
                    q4++
                }
            }
            return listOf(q1, q2, q3, q4)
        }
    }
}
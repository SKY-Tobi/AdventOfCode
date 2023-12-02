package twentyTwentyTwo.dayNine

import kotlin.math.abs

class PartOne {
    companion object {
        private const val MAP_WIDTH = 800
        private const val MAP_HEIGHT = 800
        private const val STARTING_POSITION_X = 400
        private const val STARTING_POSITION_Y = 400
        private const val UP = "U"
        private const val RIGHT = "R"
        private const val DOWN = "D"
        private const val LEFT = "L"

        fun execute(lines: List<String>) {
            val map = initializeMap()
            val pointerHead = Pointer(STARTING_POSITION_Y, STARTING_POSITION_X)
            val pointerTail = Pointer(STARTING_POSITION_Y, STARTING_POSITION_X)

            lines.forEach {
                // input[0] = direction, input[1] = amountOfSteps
                val input = it.split(" ")

                when (input[0]) {
                    UP -> stepUp(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerTail)
                    RIGHT -> stepRight(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerTail)
                    DOWN -> stepDown(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerTail)
                    LEFT -> stepLeft(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerTail)
                    else -> throw Exception("invalid direction: " + input[0])
                }

            }
            // count # (visit) appearances + 1 for the end position
            var amountOfVisits = map.trailMap.sumOf { it -> it.filter { it == '#' }.size }
            if (map.trailMap[pointerTail.y][pointerTail.x] != '#') {
                amountOfVisits++
            }
            println("$amountOfVisits positions were visited at least once by the tail")
        }

        private fun stepUp(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerTail: Pointer
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.y -= 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                if (isHeadAndTailNotTouching(pointerHead, pointerTail)) {
                    // mark old position as visited
                    trailMap[pointerTail.y][pointerTail.x] = '#'

                    // delete old and set new tail under Head position
                    visualMap[pointerTail.y][pointerTail.x] = '.'
                    pointerTail.y = pointerHead.y + 1
                    pointerTail.x = pointerHead.x
                    visualMap[pointerTail.y][pointerTail.x] = 'T'
                }
            }
        }

        private fun stepRight(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerTail: Pointer
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.x += 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                if (isHeadAndTailNotTouching(pointerHead, pointerTail)) {
                    // mark old position as visited
                    trailMap[pointerTail.y][pointerTail.x] = '#'

                    // delete old and set new tail to the left of Head position
                    visualMap[pointerTail.y][pointerTail.x] = '.'
                    pointerTail.y = pointerHead.y
                    pointerTail.x = pointerHead.x - 1
                    visualMap[pointerTail.y][pointerTail.x] = 'T'
                }
            }
        }

        private fun stepDown(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerTail: Pointer
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.y += 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                if (isHeadAndTailNotTouching(pointerHead, pointerTail)) {
                    // mark old position as visited
                    trailMap[pointerTail.y][pointerTail.x] = '#'

                    // delete old and set new tail above Head position
                    visualMap[pointerTail.y][pointerTail.x] = '.'
                    pointerTail.y = pointerHead.y - 1
                    pointerTail.x = pointerHead.x
                    visualMap[pointerTail.y][pointerTail.x] = 'T'
                }
            }
        }

        private fun stepLeft(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerTail: Pointer
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.x -= 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                if (isHeadAndTailNotTouching(pointerHead, pointerTail)) {
                    // mark old position as visited
                    trailMap[pointerTail.y][pointerTail.x] = '#'

                    // delete old and set new tail to the right of Head position
                    visualMap[pointerTail.y][pointerTail.x] = '.'
                    pointerTail.y = pointerHead.y
                    pointerTail.x = pointerHead.x + 1
                    visualMap[pointerTail.y][pointerTail.x] = 'T'
                }
            }
        }

        private fun isHeadAndTailNotTouching(pointerHead: Pointer, pointerTail: Pointer): Boolean {
            return abs(pointerHead.y - pointerTail.y) > 1 || abs(pointerHead.x - pointerTail.x) > 1
        }

        private fun initializeMap(): Map {
            val visualMap = Array(MAP_WIDTH) { CharArray(MAP_HEIGHT) }
            for (y in 0 until MAP_HEIGHT) {
                for (x in 0 until MAP_WIDTH) {
                    visualMap[y][x] = '.'
                }
            }
            visualMap[STARTING_POSITION_Y][STARTING_POSITION_X] = 's'

            return Map(visualMap, Array(MAP_WIDTH) { visualMap[it].clone() })
        }
    }
}
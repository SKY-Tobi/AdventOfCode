package dayNine

import kotlin.math.abs

class PartTwo {
    companion object {
        private const val MAP_WIDTH = 800
        private const val MAP_HEIGHT = 800
        private const val STARTING_POSITION_X = 400
        private const val STARTING_POSITION_Y = 400
        private const val UP = "U"
        private const val RIGHT = "R"
        private const val DOWN = "D"
        private const val LEFT = "L"
        private const val BODY_LENGTH = 9 // without head

        fun execute(lines: List<String>) {
            val map = initializeMap()
            val pointerHead = Pointer(STARTING_POSITION_Y, STARTING_POSITION_X)
            val pointerBody = arrayListOf<Pointer>()
            repeat(BODY_LENGTH) {
                pointerBody.add(Pointer(STARTING_POSITION_Y, STARTING_POSITION_X))
            }

            lines.stream().forEach {
                // input[0] = direction, input[1] = amountOfSteps
                val input = it.split(" ")

                when (input[0]) {
                    UP -> stepUp(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerBody)
                    RIGHT -> stepRight(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerBody)
                    DOWN -> stepDown(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerBody)
                    LEFT -> stepLeft(map.visualMap, map.trailMap, input[1].toInt(), pointerHead, pointerBody)
                    else -> throw Exception("invalid direction: " + input[0])
                }
            }
            // count # (visit) appearances + 1 for the end position if not visited (set to #) already
            var amountOfVisits = map.trailMap.sumOf { it -> it.filter { it == '#' }.size }
            if (map.trailMap[pointerBody[BODY_LENGTH - 1].y][pointerBody[BODY_LENGTH - 1].x] != '#') {
                amountOfVisits++
            }
            println("$amountOfVisits positions were visited at least once by the tail")
        }

        private fun stepUp(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerBody: List<Pointer>
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.y -= 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                val iterator = pointerBody.iterator()
                for ((index, pointer) in iterator.withIndex()) {
                    if (index == 0) {
                        if (isNotTouching(pointerHead, pointer)) {
                            // delete old and set new tail under Head position
                            visualMap[pointer.y][pointer.x] = '.'
                            pointer.y = pointerHead.y + 1
                            pointer.x = pointerHead.x
                            visualMap[pointer.y][pointer.x] = 1.digitToChar()
                        }
                    } else {
                        stepBody(
                            visualMap,
                            trailMap,
                            pointerBody[index - 1],
                            pointer,
                            index + 1 == pointerBody.size,
                            index
                        )
                    }
                }
            }
        }

        private fun stepRight(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerBody: List<Pointer>
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.x += 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                val iterator = pointerBody.iterator()
                for ((index, pointer) in iterator.withIndex()) {
                    if (index == 0) {
                        if (isNotTouching(pointerHead, pointer)) {
                            // delete old and set new tail under Head position
                            visualMap[pointer.y][pointer.x] = '.'
                            pointer.y = pointerHead.y
                            pointer.x = pointerHead.x - 1
                            visualMap[pointer.y][pointer.x] = 1.digitToChar()
                        }
                    } else {
                        stepBody(
                            visualMap,
                            trailMap,
                            pointerBody[index - 1],
                            pointer,
                            index + 1 == pointerBody.size,
                            index
                        )
                    }
                }
            }
        }

        private fun stepDown(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerBody: List<Pointer>
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.y += 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                val iterator = pointerBody.iterator()
                for ((index, pointer) in iterator.withIndex()) {
                    if (index == 0) {
                        if (isNotTouching(pointerHead, pointer)) {
                            // delete old and set new tail under Head position
                            visualMap[pointer.y][pointer.x] = '.'
                            pointer.y = pointerHead.y - 1
                            pointer.x = pointerHead.x
                            visualMap[pointer.y][pointer.x] = 1.digitToChar()
                        }
                    } else {
                        stepBody(
                            visualMap,
                            trailMap,
                            pointerBody[index - 1],
                            pointer,
                            index + 1 == pointerBody.size,
                            index
                        )
                    }
                }
            }
        }

        private fun stepLeft(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            amount: Int,
            pointerHead: Pointer,
            pointerBody: List<Pointer>
        ) {
            repeat(amount) {
                // delete old and set new Head position
                visualMap[pointerHead.y][pointerHead.x] = '.'
                pointerHead.x -= 1
                visualMap[pointerHead.y][pointerHead.x] = 'H'

                val iterator = pointerBody.iterator()
                for ((index, pointer) in iterator.withIndex()) {
                    if (index == 0) {
                        if (isNotTouching(pointerHead, pointer)) {
                            // delete old and set new tail under Head position
                            visualMap[pointer.y][pointer.x] = '.'
                            pointer.y = pointerHead.y
                            pointer.x = pointerHead.x + 1
                            visualMap[pointer.y][pointer.x] = 1.digitToChar()
                        }
                    } else {
                        stepBody(
                            visualMap,
                            trailMap,
                            pointerBody[index - 1],
                            pointer,
                            index + 1 == pointerBody.size,
                            index
                        )
                    }
                }
            }
        }

        private fun stepBody(
            visualMap: Array<CharArray>,
            trailMap: Array<CharArray>,
            pointerToCheck: Pointer,
            pointerToMove: Pointer,
            isLast: Boolean,
            index: Int
        ) {
            if (isNotTouching(pointerToCheck, pointerToMove)) {
                if (isLast) {
                    // mark old position as visited
                    trailMap[pointerToMove.y][pointerToMove.x] = '#'
                }
                // delete old pointerToMove position
                visualMap[pointerToMove.y][pointerToMove.x] = '.'

                //determine Movement
                val y = pointerToMove.y - pointerToCheck.y
                val x = pointerToMove.x - pointerToCheck.x

                // diagonal movement needed when x and y are different
                if (abs(y) > 0 && abs(x) > 0 || abs(x) > 0 && abs(y) > 0) {
                    // up right movement
                    if (y == 2 && x == -1 || x == -2 && y == 1 || y == 2 && x == -2) {
                        pointerToMove.x += 1
                        pointerToMove.y -= 1
                    }

                    // down right movement
                    if (y == -2 && x == -1 || x == -2 && y == -1 || y == -2 && x == -2) {
                        pointerToMove.x += 1
                        pointerToMove.y += 1
                    }

                    // down left movement
                    if (y == -2 && x == 1 || x == 2 && y == -1 || y == -2 && x == 2) {
                        pointerToMove.x -= 1
                        pointerToMove.y += 1
                    }

                    // up left movement
                    if (y == 2 && x == 1 || x == 2 && y == 1 || y == 2 && x == 2) {
                        pointerToMove.x -= 1
                        pointerToMove.y -= 1
                    }
                } else {
                    // up movement
                    if (y == 2) {
                        pointerToMove.y -= 1
                    }

                    // right movement
                    if (x == -2) {
                        pointerToMove.x += 1
                    }

                    // down movement
                    if (y == -2) {
                        pointerToMove.y += 1
                    }

                    // left movement
                    if (x == 2) {
                        pointerToMove.x -= 1
                    }
                }
                visualMap[pointerToMove.y][pointerToMove.x] = (index + 1).digitToChar()
            }
        }

        private fun isNotTouching(pointerOne: Pointer, pointerTwo: Pointer): Boolean {
            return abs(pointerOne.y - pointerTwo.y) > 1 || abs(pointerOne.x - pointerTwo.x) > 1
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
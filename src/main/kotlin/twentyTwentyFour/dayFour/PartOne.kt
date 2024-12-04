package twentyTwentyFour.dayFour

class PartOne {
    companion object {
        private const val XMAS_STRING = "XMAS"
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)

            var xmasCount = 0

            map.forEachIndexed { y, row ->
                row.forEachIndexed { x, _ ->
                    if (map[y][x] == XMAS_STRING[0]) {
                        Direction.values().forEach { direction ->
                            xmasCount += checkAroundForXMASString(map, x, y, 1, direction, 0)
                        }
                    }
                }
            }

            println("XMAS count: $xmasCount")
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
        }

        private fun checkAroundForXMASString(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int, directionToCheck: Direction, XMASCount: Int): Int {
            if (currentCharIndex == XMAS_STRING.length) {
                return XMASCount + 1
            }

            when(directionToCheck) {
                Direction.TOP_LEFT -> {
                    if (checkTopLeft(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.TOP_LEFT, XMASCount)
                    }
                }
                Direction.TOP -> {
                    if (checkTop(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.TOP, XMASCount)
                    }
                }
                Direction.TOP_RIGHT -> {
                    if (checkTopRight(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.TOP_RIGHT, XMASCount)
                    }
                }
                Direction.RIGHT -> {
                    if (checkRight(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.RIGHT, XMASCount)
                    }
                }
                Direction.BOTTOM_RIGHT -> {
                    if (checkBottomRight(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.BOTTOM_RIGHT, XMASCount)
                    }
                }
                Direction.BOTTOM -> {
                    if (checkBottom(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.BOTTOM, XMASCount)
                    }
                }
                Direction.BOTTOM_LEFT -> {
                    if (checkBottomLeft(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.BOTTOM_LEFT, XMASCount)
                    }
                }
                Direction.LEFT -> {
                    if (checkLeft(input, x, y, currentCharIndex)) {
                        return checkAroundForXMASString(input, x, y, currentCharIndex + 1, Direction.LEFT, XMASCount)
                    }
                }
            }
            return XMASCount
        }

        private fun checkCharacter(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            if (x < 0 || y < 0 || y >= input.size || x >= input[0].size) {
                return false
            }
            return input[y][x] == XMAS_STRING[currentCharIndex]
        }

        private fun checkTopLeft(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x - currentCharIndex, y - currentCharIndex, currentCharIndex)
        }

        private fun checkTop(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x, y - currentCharIndex, currentCharIndex)
        }

        private fun checkTopRight(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x + currentCharIndex, y - currentCharIndex, currentCharIndex)
        }

        private fun checkRight(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x + currentCharIndex, y, currentCharIndex)
        }

        private fun checkBottomRight(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x + currentCharIndex, y + currentCharIndex, currentCharIndex)
        }

        private fun checkBottom(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x, y + currentCharIndex, currentCharIndex)
        }

        private fun checkBottomLeft(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x - currentCharIndex, y + currentCharIndex, currentCharIndex)
        }

        private fun checkLeft(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            return checkCharacter(input, x - currentCharIndex, y, currentCharIndex)
        }
    }
}

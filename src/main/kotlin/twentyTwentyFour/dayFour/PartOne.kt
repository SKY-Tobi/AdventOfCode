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
                            if(checkDirectionForXMASString(map, x, y, 1, direction)) {
                                xmasCount++
                            }
                        }
                    }
                }
            }

            println("XMAS count: $xmasCount")
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
        }

        private fun checkDirectionForXMASString(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int, directionToCheck: Direction): Boolean {
            if (currentCharIndex == XMAS_STRING.length) {
                return true
            }

            val (dx, dy) = directionToCheck.getDeltas()
            val newX = x + dx * currentCharIndex
            val newY = y + dy * currentCharIndex

            return if (checkCharacter(input, newX, newY, currentCharIndex)) {
                checkDirectionForXMASString(input, x, y, currentCharIndex + 1, directionToCheck)
            } else {
                false
            }
        }

        private fun checkCharacter(input: Array<CharArray>, x: Int, y: Int, currentCharIndex: Int): Boolean {
            if (x < 0 || y < 0 || y >= input.size || x >= input[0].size) {
                return false
            }
            return input[y][x] == XMAS_STRING[currentCharIndex]
        }
    }
}

package twentyTwentyFour.dayFour

class PartTwo {
    companion object {
        private const val M = 'M'
        private const val A = 'A'
        private const val S = 'S'
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)

            var xmasCount = 0

            map.forEachIndexed { y, row ->
                row.forEachIndexed { x, _ ->
                    if (map[y][x] == A) {
                        Rotation.values().forEach { rotation ->
                            if(checkForXMASString(map, x, y, rotation)) {
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

        private fun checkForXMASString(input: Array<CharArray>, x: Int, y: Int, rotation: Rotation): Boolean {
            return when (rotation) {
                Rotation.ZERO -> checkCharacter(input, x-1, y-1, M) &&
                        checkCharacter(input, x-1, y+1, M) &&
                        checkCharacter(input, x+1, y-1, S) &&
                        checkCharacter(input, x+1, y+1, S)
                Rotation.NINETY -> checkCharacter(input, x-1, y+1, M) &&
                        checkCharacter(input, x+1, y+1, M) &&
                        checkCharacter(input, x-1, y-1, S) &&
                        checkCharacter(input, x+1, y-1, S)
                Rotation.ONE_EIGHTY -> checkCharacter(input, x-1, y-1, S) &&
                        checkCharacter(input, x-1, y+1, S) &&
                        checkCharacter(input, x+1, y-1, M) &&
                        checkCharacter(input, x+1, y+1, M)
                Rotation.TWO_SEVENTY -> checkCharacter(input, x-1, y+1, S) &&
                        checkCharacter(input, x+1, y+1, S) &&
                        checkCharacter(input, x-1, y-1, M) &&
                        checkCharacter(input, x+1, y-1, M)
            }
        }

        private fun checkCharacter(input: Array<CharArray>, x: Int, y: Int, char: Char): Boolean {
            if (x < 0 || y < 0 || y >= input.size || x >= input[0].size) {
                return false
            }
            return input[y][x] == char
        }
    }
}
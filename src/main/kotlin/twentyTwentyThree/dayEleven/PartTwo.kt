package twentyTwentyThree.dayEleven

import kotlin.math.abs

class PartTwo {
    companion object {
        private const val WEIGHT = 1000000

        fun execute(lines: List<String>) {
            val map = evaluateMap(lines)

            var sum = 0
            for (y in map.indices) {
                for (x in map[y].indices) {
                    if (map[y][x] == '#') {
                        sum += calculatePathsToAllOtherGalaxies(map, x, y)
                    }
                }
            }

            println(sum)
        }

        private fun calculatePathsToAllOtherGalaxies(map: Array<CharArray>, xGalaxy: Int, yGalaxy: Int): Int {
            var sum = 0
            for (y in yGalaxy until map.size) {
                for (x in (if (y == yGalaxy) xGalaxy + 1 else 0) until map[y].size) {
                    if (map[y][x] == '#') {
                        sum += abs(xGalaxy - x) + abs(yGalaxy - y) + (WEIGHT - 1) * countXbetweenTwoPoints(map, xGalaxy, yGalaxy, x, y)
                    }
                }
            }
            return sum
        }

        private fun countXbetweenTwoPoints(map: Array<CharArray>, x1: Int, y1: Int, x2: Int, y2: Int): Int {
            var count = 0
            var x = x1
            var y = y1

            while (x != x2) {
                if (x < x2) {
                    x++
                } else {
                    x--
                }
                if (map[y][x] == 'X') {
                    count++
                }
            }

            while (y != y2) {
                if (y < y2) {
                    y++
                } else {
                    y--
                }
                if (map[y][x] == 'X') {
                    count++
                }
            }
            return count
        }

        private fun evaluateMap(lines: List<String>): Array<CharArray> {
            val width = lines[0].toCharArray().size
            val height = lines.size
            var map = Array(height) { CharArray(width) }
            for (y in 0 until height) {
                val line = lines[y].toCharArray()
                for (x in 0 until width) {
                    map[y][x] = line[x]
                }
            }

            val rowsToExpand = mutableListOf<Int>()
            for (y in map.indices) {
                if (map[y].none { it == '#' }) {
                    rowsToExpand.add(y)
                }
            }
            rowsToExpand.forEach { y ->
                map = expandRow(map, CharArray(map[0].size) { 'X' }, y)
            }

            val colsToExpand = mutableListOf<Int>()
            for (x in map[0].indices) {
                if (map.all { it[x] != '#' }) {
                    colsToExpand.add(x)
                }
            }
            colsToExpand.forEach { x ->
                map = expandColumn(map, CharArray(map.size) { 'X' }, x)
            }

            return map
        }

        private fun expandRow(original: Array<CharArray>, newRow: CharArray, position: Int): Array<CharArray> {
            val result = original.copyOf()
            result[position] = newRow
            return result
        }

        private fun expandColumn(original: Array<CharArray>, newColumn: CharArray, position: Int): Array<CharArray> {
            val result = Array(original.size) { CharArray(original[0].size) }
            for (y in original.indices) {
                for (x in original[y].indices) {
                    result[y][x] = if (x == position) newColumn[y] else original[y][x]
                }
            }
            return result
        }
    }
}

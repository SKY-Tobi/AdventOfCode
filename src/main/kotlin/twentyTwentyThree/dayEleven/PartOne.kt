package twentyTwentyThree.dayEleven

import kotlin.math.abs

class PartOne {
    companion object {
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

        private fun calculatePathsToAllOtherGalaxies(map: Array<CharArray>, x: Int, y: Int): Int {
            var sum = 0
            for (y2 in y until map.size) {
                for (x2 in (if (y2 == y) x + 1 else 0) until map[y2].size) {
                    if (map[y2][x2] == '#') {
                        sum += abs(x - x2) + abs(y - y2)
                    }
                }
            }
            return sum
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

            val rowsToInsert = mutableListOf<Int>()
            for (y in map.indices) {
                if (map[y].none { it == '#' }) {
                    rowsToInsert.add(y)
                }
            }
            rowsToInsert.forEachIndexed { index, y ->
                map = insertRow(map, CharArray(map[0].size) { '.' }, y + index)
            }

            val colsToInsert = mutableListOf<Int>()
            for (x in map[0].indices) {
                if (map.all { it[x] != '#' }) {
                    colsToInsert.add(x)
                }
            }
            colsToInsert.forEachIndexed { index, x ->
                map = insertColumn(map, CharArray(map.size) { '.' }, x + index)
            }

            return map
        }

        private fun insertRow(original: Array<CharArray>, newRow: CharArray, position: Int): Array<CharArray> {
            return original.toMutableList().apply { add(position, newRow) }.toTypedArray()
        }

        private fun insertColumn(original: Array<CharArray>, newColumn: CharArray, position: Int): Array<CharArray> {
            return original.mapIndexed { y, row ->
                row.toMutableList().apply { add(position, newColumn[y]) }.toCharArray()
            }.toTypedArray()
        }
    }
}

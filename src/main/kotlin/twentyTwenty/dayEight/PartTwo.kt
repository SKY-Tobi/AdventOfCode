package twentyTwenty.dayEight

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val treeMap = initializeTreeMap(lines)
            val map = treeMap.treeMap
            var highestScenicScore = 0
            for (y in 0 until treeMap.height) {
                for (x in 0 until treeMap.width) {
                    val scenicScore = calculateScenicScore(map, x, y, map[y][x])
                    if (scenicScore > highestScenicScore) {
                        highestScenicScore = scenicScore
                    }
                }
            }
            println("$highestScenicScore is the highest possible scenic score")
        }

        private fun initializeTreeMap(lines: List<String>): TreeMap {
            val width = lines[0].toCharArray().size
            val height = lines.size
            val treeMap = Array(width) { IntArray(height) }
            for (y in 0 until height) {
                val line = lines[y].toCharArray()
                for (x in 0 until width) {
                    treeMap[y][x] = Character.getNumericValue(line[x])
                }
            }
            return TreeMap(treeMap, width, height)
        }

        private fun calculateScenicScore(map: Array<IntArray>, x: Int, y: Int, treeSize: Int): Int {
            // top check
            var scoreToTop = 0
            for (yy in y - 1 downTo 0) {
                if (map[yy][x] >= treeSize) {
                    scoreToTop++
                    break
                }
                scoreToTop++
            }

            // right check
            var scoreToRight = 0
            for (xx in x + 1 until map[y].size) {
                if (map[y][xx] >= treeSize) {
                    scoreToRight++
                    break
                }
                scoreToRight++
            }

            // bottom check
            var scoreToBottom = 0
            for (yy in y + 1 until map.size) {
                if (map[yy][x] >= treeSize) {
                    scoreToBottom++
                    break
                }
                scoreToBottom++
            }

            // left check
            var scoreToLeft = 0
            for (xx in x - 1 downTo 0) {
                if (map[y][xx] >= treeSize) {
                    scoreToLeft++
                    break
                }
                scoreToLeft++
            }
            return scoreToTop * scoreToRight * scoreToBottom * scoreToLeft
        }
    }
}
package twentyTwenty.dayEight

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val treeMap = initializeTreeMap(lines)
            val map = treeMap.treeMap
            var amountOfVisibleTrees = 0
            for (y in 0 until treeMap.height) {
                for (x in 0 until treeMap.width) {
                    // if tree is at the border, count amountOfVisibleTrees up as no check is needed
                    if (x == 0 || y == 0 || x == treeMap.width - 1 || y == treeMap.height - 1) {
                        amountOfVisibleTrees++
                    } else {
                        if (isTreeIsVisible(map, x, y, map[y][x])) {
                            amountOfVisibleTrees++
                        }
                    }
                }
            }
            println("$amountOfVisibleTrees trees are visible")
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

        private fun isTreeIsVisible(map: Array<IntArray>, x: Int, y: Int, treeSize: Int): Boolean {
            var isTreeVisibleToTop = true
            // top check
            for (yy in y - 1 downTo 0) {
                if (map[yy][x] >= treeSize) {
                    isTreeVisibleToTop = false
                    break
                }
            }
            var isTreeVisibleToRight = true
            // right check
            for (xx in x + 1 until map[y].size) {
                if (map[y][xx] >= treeSize) {
                    isTreeVisibleToRight = false
                    break
                }
            }
            var isTreeVisibleToBottom = true
            // bottom check
            for (yy in y + 1 until map.size) {
                if (map[yy][x] >= treeSize) {
                    isTreeVisibleToBottom = false
                    break
                }
            }
            var isTreeVisibleToLeft = true
            // left check
            for (xx in x - 1 downTo 0) {
                if (map[y][xx] >= treeSize) {
                    isTreeVisibleToLeft = false
                    break
                }
            }
            return isTreeVisibleToTop || isTreeVisibleToRight || isTreeVisibleToBottom || isTreeVisibleToLeft
        }
    }
}
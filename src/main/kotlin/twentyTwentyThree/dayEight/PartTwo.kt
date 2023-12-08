package twentyTwentyThree.dayEight

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val pattern = lines[0]
            val map = evaluateMap(lines)
            val startNodes = evaluateNotesByEndingChar(map, 'A')
            val endingNodes = evaluateNotesByEndingChar(map, 'Z')
            val currentNodes = startNodes.toMutableList()
            var steps = 0
            while (!currentNodes.all { endingNodes.contains(it) }) {
                run loop@ {
                    pattern.forEach {
                        currentNodes.forEachIndexed { index, currentNode ->
                            currentNodes[index] = if(it == 'L') {
                                map[currentNode]!!.first
                            } else {
                                map[currentNode]!!.second
                            }

                        }
                        steps++
                        if(currentNodes.all { endingNodes.contains(it) }) {
                            return@loop
                        }
                    }
                }
            }
            println("total steps needed for all nodes to reach an end node: $steps")
        }

        private fun evaluateMap(lines: List<String>): Map<String, Pair<String, String>> {
            val map = mutableMapOf<String, Pair<String, String>>()

            lines.filter { it.contains("=") }.forEach { it ->
                val nodes = mutableListOf<String>()
                val regex = Regex("[1-9A-Z]{3}")
                regex.findAll(it).forEach {
                    nodes.add(it.value)
                }
                map[nodes[0]] = Pair(nodes[1], nodes[2])
            }

            return map
        }

        private fun evaluateNotesByEndingChar(map: Map<String, Pair<String, String>>, char: Char): List<String> {
            val result = mutableListOf<String>()
            map.keys.forEach {
                if(it.last() == char) {
                    result.add(it)
                }
            }
            return result
        }
    }
}

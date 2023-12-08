package twentyTwentyThree.dayEight

class PartOne {
    companion object {
        private const val START_NODE = "AAA"
        private const val END_NODE = "ZZZ"
        
        fun execute(lines: List<String>) {
            val pattern = lines[0]
            val map = evaluateMap(lines)
            var currentNode = START_NODE
            var steps = 0
            while (currentNode != END_NODE) {
                run loop@ {
                    pattern.forEach {
                        currentNode = if(it == 'L') {
                            map[currentNode]!!.first
                        } else {
                            map[currentNode]!!.second
                        }
                        steps++
                        if(currentNode == END_NODE) {
                            return@loop
                        }
                    }
                }
            }
            println("total steps needed to reach $END_NODE: $steps")
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
    }
}


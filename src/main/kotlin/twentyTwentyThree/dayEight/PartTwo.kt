package twentyTwentyThree.dayEight

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val pattern = lines[0]
            val map = evaluateMap(lines)
            val startNodes = evaluateStartNodes(map)
            val currentNodes = startNodes.toMutableList()
            var steps = 0
            val cycles = mutableMapOf<String, List<Int>>()

            startNodes.forEach {
                cycles[it] = listOf()
            }

            while (!cycles.values.all { it.size >= 2}) {
                run loop@ {
                    pattern.forEach {
                        currentNodes.forEachIndexed { index, currentNode ->
                            currentNodes[index] = if(it == 'L') {
                                map[currentNode]!!.first
                            } else {
                                map[currentNode]!!.second
                            }
                            if(currentNodes[index].last() == 'Z') {
                                var currentList = cycles[startNodes[index]]
                                if(!currentList.isNullOrEmpty()) {
                                    currentList = currentList.toMutableList()
                                    currentList.add(steps)
                                    cycles[startNodes[index]] = currentList
                                } else {
                                    cycles[startNodes[index]] = mutableListOf(steps)
                                }
                            }
                        }
                        if(cycles.values.all { it.size >= 2}) {
                            return@loop
                        }
                        steps++
                    }
                }
            }

            val cyclesOfEachNodeToReachEndNodeAgain = cycles.map { (it.value[1] - it.value[0]).toLong() }
            println("total steps needed for all nodes to reach an end node: " + findLCMOfListOfNumbers(cyclesOfEachNodeToReachEndNodeAgain))
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

        private fun evaluateStartNodes(map: Map<String, Pair<String, String>>): List<String> {
            val result = mutableListOf<String>()
            map.keys.forEach {
                if(it.last() == 'A') {
                    result.add(it)
                }
            }
            return result
        }

        private fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
            var result = numbers[0]
            for (i in 1 until numbers.size) {
                result = findLCM(result, numbers[i])
            }
            return result
        }

        private fun findLCM(a: Long, b: Long): Long {
            val larger = if (a > b) a else b
            val maxLcm = a * b
            var lcm = larger
            while (lcm <= maxLcm) {
                if (lcm % a == 0L && lcm % b == 0L) {
                    return lcm
                }
                lcm += larger
            }
            return maxLcm
        }
    }
}

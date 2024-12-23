package twentyTwentyFour.dayTwentyThree

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val connections = mutableMapOf<String, MutableSet<String>>()

            for (line in lines) {
                val (a, b) = line.split("-")
                connections.computeIfAbsent(a) { mutableSetOf() }.add(b)
                connections.computeIfAbsent(b) { mutableSetOf() }.add(a)
            }

            val largestClique = findLargestClique(connections)
            val password = largestClique.sorted().joinToString(",")

            println("Password to get into the LAN party: $password")
        }

        private fun findLargestClique(connections: Map<String, Set<String>>): Set<String> {
            var largestClique = setOf<String>()

            for (a in connections.keys) {
                val clique = mutableSetOf(a)
                for (b in connections[a]!!) {
                    if (connections[b]!!.containsAll(clique)) {
                        clique.add(b)
                    }
                }
                if (clique.size > largestClique.size) {
                    largestClique = clique
                }
            }

            return largestClique
        }
    }
}
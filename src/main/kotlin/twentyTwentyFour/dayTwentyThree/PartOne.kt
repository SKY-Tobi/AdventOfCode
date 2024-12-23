package twentyTwentyFour.dayTwentyThree

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val connections = mutableMapOf<String, MutableSet<String>>()

            for (line in lines) {
                val (a, b) = line.split("-")
                connections.computeIfAbsent(a) { mutableSetOf() }.add(b)
                connections.computeIfAbsent(b) { mutableSetOf() }.add(a)
            }

            val setsOfThree = mutableSetOf<Set<String>>()

            for (a in connections.keys) {
                connections[a]?.forEach { b ->
                    connections[b]?.forEach { c ->
                        if (c != a && connections[c]?.contains(a) == true) {
                            setsOfThree.add(setOf(a, b, c))
                        }
                    }
                }
            }

            val filteredSets = setsOfThree.filter { set ->
                set.any { it.startsWith("t") }
            }

            println("Number of sets containing at least one computer with a name starting with 't': ${filteredSets.size}")
        }
    }
}
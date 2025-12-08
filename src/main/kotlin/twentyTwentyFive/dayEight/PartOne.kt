package twentyTwentyFive.dayEight

import kotlin.math.pow
import kotlin.math.sqrt

class PartOne {
    companion object {
        const val NUMBER_OF_JUNCTION_PAIRS = 1000

        fun execute(lines: List<String>) {
            val circuits = mutableListOf<ArrayList<Triple<Box, Box?, Long?>>>()
            val junctionBoxes = readJunctionBoxes(lines)
            val possibleConnections = calculateBoxConnections(junctionBoxes)

            val connectionsToConsider = possibleConnections.toMutableList()
            connectionsToConsider.forEach {
                circuits.add(arrayListOf(it))
            }

            do {
                var newConnectionSet = false
                connectionsToConsider.forEach { connection ->
                    val circuitsWithBox = findAllCircuitWithBox(circuits, listOf(connection.first, connection.second))
                    circuitsWithBox?.forEach { circuitWithBox ->
                        if (circuitWithBox.none { it == connection }) {
                            circuitWithBox.add(connection)
                            newConnectionSet = true
                        }
                    }
                }
            } while (newConnectionSet)

            /*connectionsToConsider.forEach {
                circuits.add(arrayListOf(Triple(it, null, null))
            }

            val unusedJunctionBoxes = junctionBoxes.filter { box ->
                circuits.none { circuit ->
                    circuit.any { triple ->
                        triple.first == box || triple.second == box
                    }
                }
            }

            unusedJunctionBoxes.forEach {
                circuits.add(arrayListOf(Triple(it, null, null)))
            }*/

            val productOfTop3 = circuits
                .map { circuit ->
                    circuit.sortedBy { it.third }
                }
                .distinct()
                .map { circuit ->
                    circuit.flatMap { listOfNotNull(it.first, it.second) }
                        .distinct()
                        .count()
                }.distinct().sortedDescending()
                .take(3)
                .reduce { acc, i -> acc * i }

            println("Product of top 3 circuits: $productOfTop3")
        }

        private fun findAllCircuitWithBox(
            circuits: List<ArrayList<Triple<Box, Box?, Long?>>>,
            boxes: List<Box>
        ): List<ArrayList<Triple<Box, Box?, Long?>>>? {
            return circuits.filter { circuit ->
                circuit.any { (box1, box2, _) ->
                    boxes.contains(box1) || boxes.contains(box2)
                }
            }
        }

        private fun calculateBoxConnections(boxes: List<Box>): List<Triple<Box, Box, Long>> {
            val result = mutableListOf<Triple<Box, Box, Long>>()
            for (i in boxes.indices) {
                for (j in i + 1 until boxes.size) {
                    val boxA = boxes[i]
                    val boxB = boxes[j]
                    val distance = sqrt(
                        (boxA.x - boxB.x).toDouble().pow(2) +
                                (boxA.y - boxB.y).toDouble().pow(2) +
                                (boxA.z - boxB.z).toDouble().pow(2)
                    ).toLong()
                    result.add(Triple(boxA, boxB, distance))
                }
            }
            return result.sortedBy { it.third }.take(NUMBER_OF_JUNCTION_PAIRS)
        }

        private fun readJunctionBoxes(lines: List<String>): List<Box> {
            val result = mutableListOf<Box>()
            val regex = Regex("(\\d+)")
            lines.forEach { line ->
                val (x, y, z) = regex.findAll(line).map { it.value.toInt() }.toList()
                result.add(Box(x, y, z))
            }
            return result
        }
    }
}
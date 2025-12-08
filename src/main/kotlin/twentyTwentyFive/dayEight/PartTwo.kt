package twentyTwentyFive.dayEight

import kotlin.math.pow
import kotlin.math.sqrt

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val junctionBoxes = readJunctionBoxes(lines)
            val possibleConnections = calculateBoxConnections(junctionBoxes)

            println(possibleConnections.first.x.toLong() * possibleConnections.second.x.toLong())
        }

        private fun calculateBoxConnections(boxes: List<Box>): Triple<Box, Box, Long> {
            val result = mutableListOf<Triple<Box, Box, Long>>()
            boxes.forEach { box ->
                val (x1, y1, z1) = box
                val (boxWithMinDistance, minDistance) = boxes
                    .filter { it != box }
                    .filter {
                        result.none { existingTriple ->
                            (existingTriple.first == box && existingTriple.second == it) ||
                                    (existingTriple.first == it && existingTriple.second == box)
                        }
                    }
                    .map { otherBox ->
                        val distance = sqrt(
                            (x1 - otherBox.x).toDouble().pow(2) +
                                    (y1 - otherBox.y).toDouble().pow(2) +
                                    (z1 - otherBox.z).toDouble().pow(2)
                        )
                        otherBox to distance.toLong()
                    }
                    .minByOrNull { it.second } ?: error("Keine Box gefunden")

                val (firstBox, secondBox) = if (box.hashCode() <= boxWithMinDistance.hashCode()) {
                    box to boxWithMinDistance
                } else {
                    boxWithMinDistance to box
                }

                result.add(Triple(firstBox, secondBox, minDistance))
            }
            return result.maxByOrNull { it.third }!!
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
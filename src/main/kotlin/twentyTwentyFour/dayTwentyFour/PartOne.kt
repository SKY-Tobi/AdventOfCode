package twentyTwentyFour.dayTwentyFour

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val parts = lines.joinToString("\n").split("\n\n")
            val wireValues = initializeWireValues(parts[0].lines())
            val gates = initializeGates(parts[1].lines())

            val pendingGates = gates.toMutableList()
            while (pendingGates.isNotEmpty()) {
                val iterator = pendingGates.iterator()
                while (iterator.hasNext()) {
                    val (inputs, operation, output) = iterator.next()
                    val value1 = wireValues[inputs.first]
                    val value2 = wireValues[inputs.second]
                    if (value1 != null && value2 != null) {
                        val result = when (operation) {
                            Operation.AND -> value1 and value2
                            Operation.OR -> value1 or value2
                            Operation.XOR -> value1 xor value2
                        }
                        wireValues[output] = result
                        iterator.remove()
                    }
                }
            }

            val binaryNumber = wireValues.filterKeys { it.startsWith("z") }
                .toSortedMap()
                .values
                .reversed()
                .joinToString("") { it.toString() }

            val decimalNumber = binaryNumber.toLong(2)
            println("Decimal number: $decimalNumber")
        }

        private fun initializeWireValues(lines: List<String>): MutableMap<String, Int> {
            val wireValues = mutableMapOf<String, Int>()
            for (line in lines) {
                val (wire, value) = line.split(": ")
                wireValues[wire] = value.toInt()
            }
            return wireValues
        }

        private fun initializeGates(lines: List<String>): MutableList<Triple<Pair<String, String>, Operation, String>> {
            val gates = mutableListOf<Triple<Pair<String, String>, Operation, String>>()
            for (line in lines) {
                val (inputs, output) = line.split(" -> ")
                val (input1, operation, input2) = inputs.split(" ")
                gates.add(Triple(Pair(input1, input2), Operation.fromString(operation), output))
            }
            return gates
        }
    }
}
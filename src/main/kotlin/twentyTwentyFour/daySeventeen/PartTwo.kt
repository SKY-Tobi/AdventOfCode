package twentyTwentyFour.daySeventeen

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val parts = lines.joinToString("\n").split("\n\n")
            val initialRegisters = Regex("\\d+").findAll(parts[0]).map { it.value.toInt() }.toList()
            val program = Regex("\\d+").findAll(parts[1]).map { it.value.toInt() }.toList()
            val (initialRegisterB, initialRegisterC) = initialRegisters.drop(1)
            var correctRegisterA = 1

            while (true) {
                var (registerA, registerB, registerC) = listOf(correctRegisterA, initialRegisterB, initialRegisterC)
                var instructionPointer = 0
                val output = mutableListOf<Int>()

                while (instructionPointer < program.size) {
                    val opcode = program[instructionPointer]
                    val operand = program.getOrElse(instructionPointer + 1) { 7 }
                    instructionPointer += if (opcode == 3 && registerA != 0) 0 else 2

                    when (opcode) {
                        0 -> registerA /= calculateDenominator(operand, registerA, registerB, registerC)
                        1 -> registerB = registerB xor operand
                        2 -> registerB = getRegisterValue(operand, registerA, registerB, registerC) % 8
                        3 -> if (registerA != 0) instructionPointer = operand
                        4 -> registerB = registerB xor registerC
                        5 -> output.add(getRegisterValue(operand, registerA, registerB, registerC) % 8)
                        6 -> registerB = registerA / calculateDenominator(operand, registerA, registerB, registerC)
                        7 -> registerC = registerA / calculateDenominator(operand, registerA, registerB, registerC)
                        else -> throw IllegalArgumentException("Invalid opcode")
                    }

                    if(output != program.dropLast(program.size - output.size)) {
                        break
                    }

                }

                if (output == program) {
                    println("Lowest positive initial value for register A: $correctRegisterA")
                    return
                }

                correctRegisterA++
            }
        }

        private fun calculateDenominator(operand: Int, registerA: Int, registerB: Int, registerC: Int): Int {
            return 1 shl when (operand) {
                in 0..3 -> operand
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalArgumentException("Invalid operand")
            }
        }

        private fun getRegisterValue(operand: Int, registerA: Int, registerB: Int, registerC: Int): Int {
            return when (operand) {
                in 0..3 -> operand
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalArgumentException("Invalid operand")
            }
        }
    }
}
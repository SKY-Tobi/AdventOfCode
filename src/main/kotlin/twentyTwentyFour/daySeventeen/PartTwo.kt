package twentyTwentyFour.daySeventeen

import kotlin.math.pow

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val parts = lines.joinToString("\n").split("\n\n")
            val program = Regex("\\d+").findAll(parts[1]).map { it.value.toLong() }.toList()

            println(findAMatchingOutput(program, program))
        }

        private fun compute(program: List<Long>, aStart: Long): MutableList<Long> {
            var registerA = aStart
            var registerB = 0L
            var registerC = 0L
            var instructionPointer = 0
            val output = mutableListOf<Long>()

            while (instructionPointer < program.size) {
                val opcode = program[instructionPointer]
                val operand = program.getOrElse(instructionPointer + 1) { 7 }
                instructionPointer += if (opcode == 3L && registerA != 0L) 0 else 2

                when (opcode) {
                    0L -> registerA /= calculateDenominator(operand, registerA, registerB, registerC)
                    1L -> registerB = registerB xor operand
                    2L -> registerB = getRegisterValue(operand, registerA, registerB, registerC) % 8
                    3L -> if (registerA != 0L) instructionPointer = operand.toInt()
                    4L -> registerB = registerB xor registerC
                    5L -> output.add(getRegisterValue(operand, registerA, registerB, registerC) % 8)
                    6L -> registerB = registerA / calculateDenominator(operand, registerA, registerB, registerC)
                    7L -> registerC = registerA / calculateDenominator(operand, registerA, registerB, registerC)
                    else -> throw IllegalArgumentException("Invalid opcode")
                }
            }

            return output
        }

        // https://www.reddit.com/r/adventofcode/comments/1hg38ah/comment/m2gfogr/
        private fun findAMatchingOutput(program: List<Long>, targetOutput: List<Long>): Long {
            var registerA = if (targetOutput.size == 1) {
                0
            } else {
                8 * findAMatchingOutput(program, targetOutput.subList(1, targetOutput.size))
            }

            while(compute(program, registerA) != targetOutput) {
                registerA++
            }

            return registerA
        }

        private fun calculateDenominator(operand: Long, registerA: Long, registerB: Long, registerC: Long): Long {
            return 2.0.pow(getRegisterValue(operand, registerA, registerB, registerC).toDouble()).toLong()
        }

        private fun getRegisterValue(operand: Long, registerA: Long, registerB: Long, registerC: Long): Long {
            return when (operand) {
                in 0..3 -> operand
                4L -> registerA
                5L -> registerB
                6L -> registerC
                else -> throw IllegalArgumentException("Invalid operand")
            }
        }
    }
}
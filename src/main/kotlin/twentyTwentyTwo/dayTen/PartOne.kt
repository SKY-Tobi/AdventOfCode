package twentyTwentyTwo.dayTen

class PartOne {
    companion object {
        private const val OPERATION_NOOP = "noop"
        private const val OPERATION_ADDX = "addx"
        private val SIGNAL_CYCLE_CHECK = intArrayOf(20, 60, 100, 140, 180, 220)
        private var signalStrength = 0

        fun execute(lines: List<String>) {
            var cycle = 0
            var cpuRegister = 1
            lines.forEach {
                // input[0] = operation, input[1] = amount (can be empty)
                val input = it.split(" ")
                if (input[0] == OPERATION_NOOP) {
                    cycle += 1
                    checkCycle(cycle, cpuRegister)
                } else if (input[0] == OPERATION_ADDX) {
                    repeat(2) {
                        cycle += 1
                        checkCycle(cycle, cpuRegister)
                    }
                    cpuRegister += input[1].toInt()
                }
            }
            println("sum of signal strengths: $signalStrength")
        }

        private fun checkCycle(cycle: Int, cpuRegister: Int) {
            if (SIGNAL_CYCLE_CHECK.contains(cycle)) {
                println(cycle.toString() + "th cycle : " + cpuRegister * cycle)
                signalStrength += cpuRegister * cycle
            }
        }
    }
}

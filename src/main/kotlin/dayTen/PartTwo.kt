package dayTen

import kotlin.math.abs

class PartTwo {
    companion object {
        private const val OPERATION_NOOP = "noop"
        private const val OPERATION_ADDX = "addx"
        private const val CYCLE_STEPS = 40
        private const val SPRITE_OFFSET = 1
        private val SIGNAL_CYCLE_CHECK = intArrayOf(20, 60, 100, 140, 180, 220)
        private var signalStrength = 0
        private var crt = initializeCRT()

        fun execute(lines: List<String>) {
            var cycle = 0
            var cpuRegister = 1
            lines.forEachIndexed { index, it ->
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

            println("crt output:")
            crt.forEach { it ->
                it.forEach { print(it) }
                println()
            }

            println("sum of signal strengths: $signalStrength")
        }

        private fun checkCycle(cycle: Int, cpuRegister: Int) {
            // cycle 1 - 40 = Height 0 = Index 0 - 39
            // cycle 41 - 80 = Height 1 = Index 40 - 79...
            val crtHeightPosition = (cycle - 1) / CYCLE_STEPS

            // cycle 1-40 = Index 0 - 39 = (0 - 39) - 0 = 0 - 39
            // cycle 41 - 80 = Index 40 - 79 = (40 - 79) - 1 * 40 = 0 - 39
            val crtWidthPosition = (cycle - 1) - CYCLE_STEPS * crtHeightPosition

            // checks if cpuRegister is within the sprite position (since the sprite is multiple pixels wide)
            // its possible to be SPRITE_OFFSET off the crtWidthPosition
            if (abs(cpuRegister - crtWidthPosition) <= SPRITE_OFFSET) {
                crt[crtHeightPosition][crtWidthPosition] = '#'
            }

            if (SIGNAL_CYCLE_CHECK.contains(cycle)) {
                println(cycle.toString() + "th cycle : " + cpuRegister * cycle)
                signalStrength += cpuRegister * cycle
            }
        }

        private fun initializeCRT(): Array<CharArray> {
            val width = 40
            val height = 6
            val crt = Array(height) { CharArray(width) }
            for (y in 0 until height) {
                for (x in 0 until width) {
                    crt[y][x] = '.'
                }
            }
            return crt
        }
    }
}

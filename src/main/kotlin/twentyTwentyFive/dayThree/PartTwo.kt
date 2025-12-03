package twentyTwentyFive.dayThree

class PartTwo {
    companion object {
        const val STACK_SIZE = 12

        fun execute(lines: List<String>) {
            var total = 0L
            lines.forEach { banks ->
                val stack = List(STACK_SIZE) { 0 }.toMutableList()
                banks.forEachIndexed { digitIndex, it ->
                    val currentDigit = it.digitToInt()
                    val firstSmallerDigitIndex = stack.withIndex().indexOfFirst { (stackIndex, stackDigit) -> stackDigit < currentDigit && digitIndex + STACK_SIZE - stackIndex <= banks.length }
                    if (firstSmallerDigitIndex != -1) {
                        stack[firstSmallerDigitIndex] = currentDigit
                        stack.subList(firstSmallerDigitIndex + 1, STACK_SIZE).fill(0)
                    }
                }
                total += stack.joinToString("") { it.toString() }.toLong()
            }
            println("total joltage: $total")
        }
    }
}
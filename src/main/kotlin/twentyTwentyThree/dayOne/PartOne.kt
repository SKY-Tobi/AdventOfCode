package twentyTwentyThree.dayOne

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0
            val regex = Regex("\\D*")
            lines.forEach { it ->
                val digits = it.split(regex).filter { it.isNotEmpty() }
                sum += if(digits.size == 1) {
                    Integer.parseInt(digits[0]) * 10 + Integer.parseInt(digits[0])
                } else {
                    Integer.parseInt(digits[0]) * 10 + Integer.parseInt(digits[digits.size - 1])
                }
            }
            println("calibration value: $sum")
        }
    }
}

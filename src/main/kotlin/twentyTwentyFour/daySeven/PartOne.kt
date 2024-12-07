package twentyTwentyFour.daySeven

class PartOne {
    companion object {
        private val OPERATIONS = listOf("*", "+")
        fun execute(lines: List<String>) {
            val regexNumbers = Regex("\\d+")
            val numbers = lines.map { line ->
                regexNumbers.findAll(line).map { it.value }.map { it.toLong() }.toMutableList()
            }
            val result = numbers.sumOf {
                val resultToHit = it.removeAt(0)
                val results = mutableListOf(it.removeAt(0))

                it.forEach { current ->
                    val newResults = mutableListOf<Long>()
                    results.forEach { result ->
                        OPERATIONS.forEach { operation ->
                            when (operation) {
                                "*" -> newResults.add(result * current)
                                "+" -> newResults.add(result + current)
                            }
                        }
                    }
                    results.clear()
                    results.addAll(newResults.filter { newResult -> newResult <= resultToHit })
                }

                if(results.contains(resultToHit)) resultToHit else 0
            }
            println("calibration result: $result")
        }
    }
}
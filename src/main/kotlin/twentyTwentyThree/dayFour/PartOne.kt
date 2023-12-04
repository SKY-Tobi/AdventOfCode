package twentyTwentyThree.dayFour

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0

            lines.forEach {
                val input = it.split(":")[1].split("|")
                val pair = Pair(convertNumbersToList(input[0]), convertNumbersToList(input[1]))
                var points = 0;
                pair.second.forEach { number ->
                    if(pair.first.contains(number)){
                        if(points == 0) {
                            points++
                        } else {
                            points *= 2
                        }
                    }
                }
                sum += points
            }

            println("total points worth: $sum")
        }

        private fun convertNumbersToList(numbers: String): List<Int> {
            val numbersList = mutableListOf<Int>()
            numbers.split(" ").filter { it.isNotEmpty() }.forEach { numbersList.add(Integer.parseInt(it)) }
            return numbersList
        }
    }
}

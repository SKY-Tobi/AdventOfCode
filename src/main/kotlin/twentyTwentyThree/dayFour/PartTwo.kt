package twentyTwentyThree.dayFour

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0
            val copies = mutableMapOf<Int, Int>()

            lines.forEachIndexed { index, it ->
                val input = it.split(":")[1].split("|")
                val pair = Pair(convertNumbersToList(input[0]), convertNumbersToList(input[1]))
                var matchingNumbers = 0

                pair.second.forEach { number ->
                    if(pair.first.contains(number)){
                        matchingNumbers++
                    }
                }

                repeat(matchingNumbers) { cardNumber ->
                    val currentCopies = copies[index]
                    if(currentCopies == null) {
                        copies[index + cardNumber + 1] = 1
                    } else {
                        copies[index + cardNumber + 1] = (1 + currentCopies) + copies.getOrDefault(index + cardNumber + 1, 0)
                    }
                }

                sum += copies.getOrDefault(index, 0) + 1
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

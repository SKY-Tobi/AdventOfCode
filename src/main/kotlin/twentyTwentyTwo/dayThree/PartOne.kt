package twentyTwentyTwo.dayThree

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0
            lines.forEach { it ->
                val mid: Int = it.length / 2
                val firstPart = it.substring(0, mid)
                val secondPart = it.substring(mid)
                val commonCharacters =
                    firstPart.split("").filter { it.isNotEmpty() && secondPart.contains(it) }.distinct()
                if (commonCharacters.size == 1) {
                    sum += Priority.getPriorityByName(commonCharacters[0])
                } else {
                    throw Exception("invalid amount of common Characters")
                }
            }
            println("total sum: $sum")
        }
    }
}
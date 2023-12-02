package twentyTwenty.dayThree

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0
            var firstElf = ""
            var secondElf = ""
            var thirdElf: String
            lines.forEachIndexed { index, it ->
                when ((index + 1) % 3) {
                    1 -> firstElf = it
                    2 -> secondElf = it
                    else -> {
                        thirdElf = it
                        val commonCharacters =
                            firstElf.split("")
                                .filter { it.isNotEmpty() && secondElf.contains(it) && thirdElf.contains(it) }
                                .distinct()
                        if (commonCharacters.size == 1) {
                            sum += Priority.getPriorityByName(commonCharacters[0])
                        } else {
                            throw Exception("invalid amount of common Characters")
                        }
                    }
                }
            }
            println("total sum: $sum")
        }
    }
}
package twentyTwentyFour.dayOne

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val (list1, list2) = lines.map { it.split(Regex("\\s+")).map(String::toInt) }.map { it[0] to it[1] }.unzip()
            val sum = list1.sorted().sumOf { it1 -> it1 * list2.count { it2 -> it1 == it2 } }

            println("similarity score: $sum")
        }
    }
}

package twentyTwentyFour.dayOne

import kotlin.math.abs

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val (list1, list2) = lines.map { it.split(Regex("\\s+")).map(String::toInt) }.map { it[0] to it[1] }.unzip()
            val sum = list1.sorted().zip(list2.sorted()).sumOf { (it1, it2) -> abs(it1 - it2) }

            println("total distance: $sum")
        }
    }
}
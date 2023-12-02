package twentyTwentyThree

import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    print("enter day number or \"all\": ")
    when (readlnOrNull()) {
        "1" -> dayOne()
        "all" -> {
            dayOne()
        }
        else -> println("invalid input")
    }
}

fun dayOne() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    twentyTwentyThree.dayOne.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayOne.PartTwo.execute(lines)
    println()
}
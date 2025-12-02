package twentyTwentyFive

import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    print("enter day number or \"all\": ")
    when (readlnOrNull()) {
        "1" -> dayOne()
        "2" -> dayTwo()
        "all" -> {
            dayOne()
            dayTwo()
        }
        else -> println("invalid input")
    }
}

fun dayOne() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2025/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    twentyTwentyFive.dayOne.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFive.dayOne.PartTwo.execute(lines)
    println()
}

fun dayTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2025/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    twentyTwentyFive.dayTwo.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFive.dayTwo.PartTwo.execute(lines)
    println()
}
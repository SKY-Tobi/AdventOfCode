package twentyTwentyFour

import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    print("enter day number or \"all\": ")
    when (readlnOrNull()) {
        "1" -> dayOne()
        "2" -> dayTwo()
        "all" -> {
            dayOne()
        }
        else -> println("invalid input")
    }
}

fun dayOne() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    twentyTwentyFour.dayOne.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayOne.PartTwo.execute(lines)
    println()
}

fun dayTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    twentyTwentyFour.dayTwo.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwo.PartTwo.execute(lines)
    println()
}
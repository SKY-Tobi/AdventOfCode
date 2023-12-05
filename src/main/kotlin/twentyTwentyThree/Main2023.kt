package twentyTwentyThree

import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    print("enter day number or \"all\": ")
    when (readlnOrNull()) {
        "1" -> dayOne()
        "2" -> dayTwo()
        "3" -> dayThree()
        "4" -> dayFour()
        "5" -> dayFive()
        "all" -> {
            dayOne()
            dayTwo()
            dayThree()
            dayFour()
            dayFive()
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

fun dayTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    twentyTwentyThree.dayTwo.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayTwo.PartTwo.execute(lines)
    println()
}

fun dayThree() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayThreeInput.txt")
    println("Day 3:")
    println("Part One:")
    twentyTwentyThree.dayThree.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayThree.PartTwo.execute(lines)
    println()
}

fun dayFour() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayFourInput.txt")
    println("Day 4:")
    println("Part One:")
    twentyTwentyThree.dayFour.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayFour.PartTwo.execute(lines)
    println()
}

fun dayFive() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayFiveInput.txt")
    println("Day 4:")
    println("Part One:")
    twentyTwentyThree.dayFive.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayFive.PartTwo.execute(lines)
    println()
}
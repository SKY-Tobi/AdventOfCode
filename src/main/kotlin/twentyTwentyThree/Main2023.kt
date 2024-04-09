package twentyTwentyThree

import FileReader.Companion.readFileAsLinesUsingReadLines

//test
fun main() {
    print("enter day number or \"all\": ")
    when (readlnOrNull()) {
        "1" -> dayOne()
        "2" -> dayTwo()
        "3" -> dayThree()
        "4" -> dayFour()
        "5" -> dayFive()
        "6" -> daySix()
        "7" -> daySeven()
        "8" -> dayEight()
        "9" -> dayNine()
        "10" -> dayTen()
        "all" -> {
            dayOne()
            dayTwo()
            dayThree()
            dayFour()
            dayFive()
            daySix()
            daySeven()
            dayEight()
            dayNine()
            dayTen()
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
    println("Day 5:")
    println("Part One:")
    twentyTwentyThree.dayFive.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayFive.PartTwo.execute(lines)
    println()
}

fun daySix() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DaySixInput.txt")
    println("Day 6:")
    println("Part One:")
    twentyTwentyThree.daySix.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.daySix.PartTwo.execute(lines)
    println()
}

fun daySeven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DaySevenInput.txt")
    println("Day 7:")
    println("Part One:")
    twentyTwentyThree.daySeven.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.daySeven.PartTwo.execute(lines)
    println()
}

fun dayEight() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayEightInput.txt")
    println("Day 8:")
    println("Part One:")
    twentyTwentyThree.dayEight.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayEight.PartTwo.execute(lines)
    println()
}

fun dayNine() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayNineInput.txt")
    println("Day 9:")
    println("Part One:")
    twentyTwentyThree.dayNine.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayNine.PartTwo.execute(lines)
    println()
}

fun dayTen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2023/DayTenInput.txt")
    println("Day 10:")
    println("Part One:")
    twentyTwentyThree.dayTen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyThree.dayTen.PartTwo.execute(lines)
    println()
}
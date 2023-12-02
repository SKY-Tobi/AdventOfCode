package twentyTwenty

import FileReader.Companion.readFileAsLinesUsingReadLines
import twentyTwenty.daySix.PartOne
import twentyTwenty.dayTen.PartTwo

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
        "11" -> dayEleven()
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
            dayEleven()
        }
        else -> println("invalid input")
    }
}

fun dayOne() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    twentyTwenty.dayOne.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayOne.PartTwo.execute(lines)
    println()
}

fun dayTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    twentyTwenty.dayTwo.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayTwo.PartTwo.execute(lines)
    println()
}

fun dayThree() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayThreeInput.txt")
    println("Day 3:")
    println("Part One:")
    twentyTwenty.dayThree.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayThree.PartTwo.execute(lines)
    println()
}

fun dayFour() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayFourInput.txt")
    println("Day 4:")
    println("Part One:")
    twentyTwenty.dayFour.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayFour.PartTwo.execute(lines)
    println()
}

fun dayFive() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayFiveInput.txt")
    println("Day 5:")
    println("Part One:")
    twentyTwenty.dayFive.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayFive.PartTwo.execute(lines)
    println()
}

fun daySix() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DaySixInput.txt")
    println("Day 6:")
    println("Part One:")
    PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.daySix.PartTwo.execute(lines)
    println()
}

fun daySeven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DaySevenInput.txt")
    println("Day 7:")
    println("Part One:")
    twentyTwenty.daySeven.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.daySeven.PartTwo.execute(lines)
    println()
}

fun dayEight() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayEightInput.txt")
    println("Day 8:")
    println("Part One:")
    twentyTwenty.dayEight.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayEight.PartTwo.execute(lines)
    println()
}

fun dayNine() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayNineInput.txt")
    println("Day 9:")
    println("Part One:")
    twentyTwenty.dayNine.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayNine.PartTwo.execute(lines)
    println()
}

fun dayTen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayTenInput.txt")
    println("Day 10:")
    println("Part One:")
    twentyTwenty.dayTen.PartOne.execute(lines)
    println("Part Two:")
    PartTwo.execute(lines)
    println()
}

fun dayEleven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2022/DayElevenInput.txt")
    println("Day 11:")
    println("Part One:")
    twentyTwenty.dayEleven.PartOne.execute(lines)
    println("Part Two:")
    twentyTwenty.dayEleven.PartTwo.execute(lines)
    println()
}
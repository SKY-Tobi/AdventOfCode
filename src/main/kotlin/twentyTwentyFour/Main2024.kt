package twentyTwentyFour

import FileReader.Companion.readFileAsLinesUsingReadLines

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

fun dayThree() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayThreeInput.txt")
    println("Day 3:")
    println("Part One:")
    twentyTwentyFour.dayThree.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayThree.PartTwo.execute(lines)
    println()
}

fun dayFour() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayFourInput.txt")
    println("Day 4:")
    println("Part One:")
    twentyTwentyFour.dayFour.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayFour.PartTwo.execute(lines)
    println()
}

fun dayFive() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayFiveInput.txt")
    println("Day 5:")
    println("Part One:")
    twentyTwentyFour.dayFive.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayFive.PartTwo.execute(lines)
    println()
}

fun daySix() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DaySixInput.txt")
    println("Day 6:")
    println("Part One:")
    twentyTwentyFour.daySix.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.daySix.PartTwo.execute(lines)
    println()
}

fun daySeven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DaySevenInput.txt")
    println("Day 7:")
    println("Part One:")
    twentyTwentyFour.daySeven.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.daySeven.PartTwo.execute(lines)
    println()
}

fun dayEight() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayEightInput.txt")
    println("Day 8:")
    println("Part One:")
    twentyTwentyFour.dayEight.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayEight.PartTwo.execute(lines)
    println()
}

fun dayNine() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayNineInput.txt")
    println("Day 9:")
    println("Part One:")
    twentyTwentyFour.dayNine.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayNine.PartTwo.execute(lines)
    println()
}

fun dayTen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTenInput.txt")
    println("Day 10:")
    println("Part One:")
    twentyTwentyFour.dayTen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTen.PartTwo.execute(lines)
    println()
}

fun dayEleven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayElevenInput.txt")
    println("Day 11:")
    println("Part One:")
    twentyTwentyFour.dayEleven.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayEleven.PartTwo.execute(lines)
    println()
}
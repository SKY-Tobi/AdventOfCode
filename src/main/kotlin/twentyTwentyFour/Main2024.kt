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
        "12" -> dayTwelve()
        "13" -> dayThirteen()
        "14" -> dayFourteen()
        "15" -> dayFifteen()
        "16" -> daySixteen()
        "17" -> daySeventeen()
        "18" -> dayEighteen()
        "19" -> dayNineteen()
        "20" -> dayTwenty()
        "21" -> dayTwentyOne()
        "22" -> dayTwentyTwo()
        "23" -> dayTwentyThree()
        "24" -> dayTwentyFour()
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
            dayTwelve()
            dayThirteen()
            dayFourteen()
            dayFifteen()
            daySixteen()
            daySeventeen()
            dayEighteen()
            dayNineteen()
            dayTwenty()
            dayTwentyOne()
            dayTwentyTwo()
            dayTwentyThree()
            dayTwentyFour()
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

fun dayTwelve() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwelveInput.txt")
    println("Day 12:")
    println("Part One:")
    twentyTwentyFour.dayTwelve.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwelve.PartTwo.execute(lines)
    println()
}

fun dayThirteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayThirteenInput.txt")
    println("Day 13:")
    println("Part One:")
    twentyTwentyFour.dayThirteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayThirteen.PartTwo.execute(lines)
    println()
}

fun dayFourteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayFourteenInput.txt")
    println("Day 14:")
    println("Part One:")
    twentyTwentyFour.dayFourteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayFourteen.PartTwo.execute(lines)
    println()
}

fun dayFifteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayFifteenInput.txt")
    println("Day 15:")
    println("Part One:")
    twentyTwentyFour.dayFifteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayFifteen.PartTwo.execute(lines)
    println()
}

fun daySixteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DaySixteenInput.txt")
    println("Day 16:")
    println("Part One:")
    twentyTwentyFour.daySixteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.daySixteen.PartTwo.execute(lines)
    println()
}

fun daySeventeen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DaySeventeenInput.txt")
    println("Day 17:")
    println("Part One:")
    twentyTwentyFour.daySeventeen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.daySeventeen.PartTwo.execute(lines)
    println()
}

fun dayEighteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayEighteenInput.txt")
    println("Day 18:")
    println("Part One:")
    twentyTwentyFour.dayEighteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayEighteen.PartTwo.execute(lines)
    println()
}

fun dayNineteen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayNineTeenInput.txt")
    println("Day 19:")
    println("Part One:")
    twentyTwentyFour.dayNineteen.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayNineteen.PartTwo.execute(lines)
    println()
}

fun dayTwenty() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwentyInput.txt")
    println("Day 20:")
    println("Part One:")
    twentyTwentyFour.dayTwenty.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwenty.PartTwo.execute(lines)
    println()
}

fun dayTwentyOne() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwentyOneInput.txt")
    println("Day 21:")
    println("Part One:")
    twentyTwentyFour.dayTwentyOne.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwentyOne.PartTwo.execute(lines)
    println()
}

fun dayTwentyTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwentyTwoInput.txt")
    println("Day 22:")
    println("Part One:")
    twentyTwentyFour.dayTwentyTwo.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwentyTwo.PartTwo.execute(lines)
    println()
}

fun dayTwentyThree() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwentyThreeInput.txt")
    println("Day 23:")
    println("Part One:")
    twentyTwentyFour.dayTwentyThree.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwentyThree.PartTwo.execute(lines)
    println()
}

fun dayTwentyFour() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/2024/DayTwentyFourInput.txt")
    println("Day 24:")
    println("Part One:")
    twentyTwentyFour.dayTwentyFour.PartOne.execute(lines)
    println("Part Two:")
    twentyTwentyFour.dayTwentyFour.PartTwo.execute(lines)
    println()
}
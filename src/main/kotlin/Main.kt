import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    print("enter day number or \"all\": ")
    when (readLine()) {
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
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    dayOne.PartOne.execute(lines)
    println("Part Two:")
    dayOne.PartTwo.execute(lines)
    println()
}

fun dayTwo() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    dayTwo.PartOne.execute(lines)
    println("Part Two:")
    dayTwo.PartTwo.execute(lines)
    println()
}

fun dayThree() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayThreeInput.txt")
    println("Day 3:")
    println("Part One:")
    dayThree.PartOne.execute(lines)
    println("Part Two:")
    dayThree.PartTwo.execute(lines)
    println()
}

fun dayFour() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayFourInput.txt")
    println("Day 4:")
    println("Part One:")
    dayFour.PartOne.execute(lines)
    println("Part Two:")
    dayFour.PartTwo.execute(lines)
    println()
}

fun dayFive() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayFiveInput.txt")
    println("Day 5:")
    println("Part One:")
    dayFive.PartOne.execute(lines)
    println("Part Two:")
    dayFive.PartTwo.execute(lines)
    println()
}

fun daySix() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DaySixInput.txt")
    println("Day 6:")
    println("Part One:")
    daySix.PartOne.execute(lines)
    println("Part Two:")
    daySix.PartTwo.execute(lines)
    println()
}

fun daySeven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DaySevenInput.txt")
    println("Day 7:")
    println("Part One:")
    daySeven.PartOne.execute(lines)
    println("Part Two:")
    daySeven.PartTwo.execute(lines)
    println()
}

fun dayEight() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayEightInput.txt")
    println("Day 8:")
    println("Part One:")
    dayEight.PartOne.execute(lines)
    println("Part Two:")
    dayEight.PartTwo.execute(lines)
    println()
}

fun dayNine() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayNineInput.txt")
    println("Day 9:")
    println("Part One:")
    dayNine.PartOne.execute(lines)
    println("Part Two:")
    dayNine.PartTwo.execute(lines)
    println()
}

fun dayTen() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayTenInput.txt")
    println("Day 10:")
    println("Part One:")
    dayTen.PartOne.execute(lines)
    println("Part Two:")
    dayTen.PartTwo.execute(lines)
    println()
}

fun dayEleven() {
    val lines = readFileAsLinesUsingReadLines("src/main/resources/DayElevenInput.txt")
    println("Day 11:")
    println("Part One:")
    dayEleven.PartOne.execute(lines)
    println("Part Two:")
    dayEleven.PartTwo.execute(lines)
    println()
}
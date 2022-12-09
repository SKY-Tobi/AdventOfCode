import FileReader.Companion.readFileAsLinesUsingReadLines

fun main() {
    var lines = readFileAsLinesUsingReadLines("src/main/resources/DayOneInput.txt")
    println("Day 1:")
    println("Part One:")
    dayOne.PartOne.execute(lines)
    println("Part Two:")
    dayOne.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DayTwoInput.txt")
    println("Day 2:")
    println("Part One:")
    dayTwo.PartOne.execute(lines)
    println("Part Two:")
    dayTwo.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DayThreeInput.txt")
    println("Day 3:")
    println("Part One:")
    dayThree.PartOne.execute(lines)
    println("Part Two:")
    dayThree.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DayFourInput.txt")
    println("Day 4:")
    println("Part One:")
    dayFour.PartOne.execute(lines)
    println("Part Two:")
    dayFour.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DayFiveInput.txt")
    println("Day 5:")
    println("Part One:")
    dayFive.PartOne.execute(lines)
    println("Part Two:")
    dayFive.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DaySixInput.txt")
    println("Day 6:")
    println("Part One:")
    daySix.PartOne.execute(lines)
    println("Part Two:")
    daySix.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DaySevenInput.txt")
    println("Day 7:")
    println("Part One:")
    daySeven.PartOne.execute(lines)
    println("Part Two:")
    daySeven.PartTwo.execute(lines)
    println()

    lines = readFileAsLinesUsingReadLines("src/main/resources/DayEightInput.txt")
    println("Day 8:")
    println("Part One:")
    dayEight.PartOne.execute(lines)
    println("Part Two:")
    dayEight.PartTwo.execute(lines)
    println()
}
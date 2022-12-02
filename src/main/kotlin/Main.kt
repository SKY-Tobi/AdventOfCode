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
}
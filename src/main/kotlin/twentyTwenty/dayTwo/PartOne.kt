package twentyTwenty.dayTwo

private const val ELF_ROCK = "A"
private const val ELF_PAPER = "B"
private const val ELF_SCISSORS = "C"

private const val ME_ROCK = "X"
private const val ME_PAPER = "Y"
private const val ME_SCISSORS = "Z"

private const val ROCK = "Rock"
private const val PAPER = "Paper"
private const val SCISSORS = "Scissors"

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var score = 0
            lines.stream().forEach {
                val input = it.split(" ")
                val elf = when (input[0]) {
                    ELF_ROCK -> ROCK
                    ELF_PAPER -> PAPER
                    ELF_SCISSORS -> SCISSORS
                    else -> throw Exception("Invalid Input")
                }
                val me = when (input[1]) {
                    ME_ROCK -> ROCK
                    ME_PAPER -> PAPER
                    ME_SCISSORS -> SCISSORS
                    else -> throw Exception("Invalid Input")
                }

                //evaluate points for battle
                if (elf == me) {
                    score += 3
                } else if ((elf == ROCK && me == PAPER) || (elf == PAPER && me == SCISSORS) || (elf == SCISSORS && me == ROCK)) {
                    score += 6
                }

                //evaluate selected tool
                score += when (me) {
                    ROCK -> 1
                    PAPER -> 2
                    SCISSORS -> 3
                    else -> throw Exception("Invalid Input")
                }
            }
            println("total score: $score")
        }
    }
}
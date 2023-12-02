package twentyTwentyTwo.dayTwo

private const val ELF_ROCK = "A"
private const val ELF_PAPER = "B"
private const val ELF_SCISSORS = "C"

private const val LOSE = "X"
private const val DRAW = "Y"
private const val WIN = "Z"

private const val ROCK = "Rock"
private const val PAPER = "Paper"
private const val SCISSORS = "Scissors"

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var score = 0
            lines.forEach {
                val input = it.split(" ")
                val elf = when (input[0]) {
                    ELF_ROCK -> ROCK
                    ELF_PAPER -> PAPER
                    ELF_SCISSORS -> SCISSORS
                    else -> throw Exception("Invalid Input")
                }

                //calculate my input
                val me: String = when (input[1]) {
                    LOSE -> calculateMyInput(elf, LOSE)
                    DRAW -> elf
                    WIN -> calculateMyInput(elf, WIN)
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

        private fun calculateMyInput(elfInput: String, condition: String): String {
            when (elfInput) {
                ROCK -> when (condition) {
                    LOSE -> return SCISSORS
                    WIN -> return PAPER
                }

                PAPER -> when (condition) {
                    LOSE -> return ROCK
                    WIN -> return SCISSORS
                }

                SCISSORS -> when (condition) {
                    LOSE -> return PAPER
                    WIN -> return ROCK
                }

                else -> throw Exception("Invalid Input")
            }
            return ""
        }
    }
}
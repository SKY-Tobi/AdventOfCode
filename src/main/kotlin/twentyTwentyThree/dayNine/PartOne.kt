package twentyTwentyThree.dayNine

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val historySequences = mutableListOf<List<List<Long>>>()
            lines.forEach {
                val rootNumbers = it.split(" ").map { number -> number.toLong() }
                historySequences.add(evaluateDifferences(rootNumbers, mutableListOf(rootNumbers)))
            }

            var sumOfHistories = 0L
            historySequences.forEach {
                sumOfHistories += evaluateHistoryValue(it, 0L, it.size - 1)
            }

            println("sum of all histories: $sumOfHistories")
        }

        private fun evaluateDifferences(listOfNumbersToProve: List<Long>, sequences: MutableList<List<Long>> ): List<List<Long>> {
            val sequence = mutableListOf<Long>()
            repeat(listOfNumbersToProve.size) { index ->
                if(index + 1 < listOfNumbersToProve.size) {
                    sequence.add(listOfNumbersToProve[index + 1] - listOfNumbersToProve[index])
                }
            }
            sequences.add(sequence)
            return if(sequence.all { it == 0L }) {
                sequences
            } else {
                evaluateDifferences(sequence, sequences)
            }
        }

        private fun evaluateHistoryValue(history: List<List<Long>>, valueOfSequenceBefore: Long, index: Int): Long {
            val lastNumberOfHistorySequence = history[index][history[index].size - 1]
            return if(0 == index) {
                lastNumberOfHistorySequence + valueOfSequenceBefore
            } else {
                evaluateHistoryValue(history,  lastNumberOfHistorySequence + valueOfSequenceBefore, index - 1)
            }
        }
    }
}

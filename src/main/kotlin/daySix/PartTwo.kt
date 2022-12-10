package daySix

class PartTwo {
    companion object {
        private const val AMOUNT_OF_UNIQUE_CHARACTERS = 14

        fun execute(lines: List<String>) {
            var markerLocation = 0
            val datastream = lines[0].split("")
            run breaker@{
                datastream.forEachIndexed { index, it ->
                    // continue if there aren't enough amount of characters to form a packet
                    if (index < AMOUNT_OF_UNIQUE_CHARACTERS) {
                        return@forEachIndexed
                    }
                    val amountOfUniqueCharactersFound = mutableListOf<String>()
                    for (i in 0 until AMOUNT_OF_UNIQUE_CHARACTERS) {
                        amountOfUniqueCharactersFound.add(datastream[index - i])
                    }

                    if (amountOfUniqueCharactersFound.distinct().size == AMOUNT_OF_UNIQUE_CHARACTERS) {
                        markerLocation = index
                        return@breaker
                    }
                }
            }
            if (markerLocation == 0) {
                throw Exception("Invalid marker location")
            }
            println("start-of-message marker location: $markerLocation")
        }
    }
}

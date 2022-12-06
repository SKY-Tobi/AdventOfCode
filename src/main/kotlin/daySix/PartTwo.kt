package daySix

class PartTwo {
    companion object {
        private const val amountOfUniqueCharacters = 14

        fun execute(lines: List<String>) {
            var markerLocation = 0
            val datastream = lines[0].split("")
            run breaker@{
                datastream.forEachIndexed { index, it ->
                    // continue if there aren't enough amount of characters to form a packet
                    if (index < amountOfUniqueCharacters) {
                        return@forEachIndexed
                    }
                    val amountOfUniqueCharactersFound = mutableListOf<String>()
                    for (i in 0 until amountOfUniqueCharacters) {
                        amountOfUniqueCharactersFound.add(datastream[index - i])
                    }

                    if (amountOfUniqueCharactersFound.distinct().size == amountOfUniqueCharacters) {
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

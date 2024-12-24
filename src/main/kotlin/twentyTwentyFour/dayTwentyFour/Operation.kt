package twentyTwentyFour.dayTwentyFour

enum class Operation {
    AND, XOR, OR;

    companion object {
        fun fromString(string: String): Operation {
            return when (string) {
                "AND" -> AND
                "XOR" -> XOR
                "OR" -> OR
                else -> throw IllegalArgumentException("Unknown operation: $string")
            }
        }
    }
}
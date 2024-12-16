package twentyTwentyFour.daySixteen

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun getPossibleDirections(): List<Triple<Direction, Pair<Int, Int>, Int>> {
        return when (this) {
            NORTH -> listOf(Triple(NORTH, NORTH.getDeltas(), 0), Triple(EAST, EAST.getDeltas(), 1), Triple(WEST, WEST.getDeltas(), 1))
            EAST -> listOf(Triple(EAST, EAST.getDeltas(), 0), Triple(SOUTH, SOUTH.getDeltas(), 1), Triple(NORTH, NORTH.getDeltas(), 1))
            SOUTH -> listOf(Triple(SOUTH, SOUTH.getDeltas(), 0), Triple(WEST, WEST.getDeltas(), 1), Triple(EAST, EAST.getDeltas(), 1))
            WEST -> listOf(Triple(WEST, WEST.getDeltas(), 0), Triple(NORTH, NORTH.getDeltas(), 1), Triple(SOUTH, SOUTH.getDeltas(), 1))
        }
    }

    fun getDeltas(): Pair<Int, Int> {
        return when (this) {
            NORTH -> Pair(0, -1)
            EAST -> Pair(1, 0)
            SOUTH -> Pair(0, 1)
            WEST -> Pair(-1, 0)
        }
    }
}
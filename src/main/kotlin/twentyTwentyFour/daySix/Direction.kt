package twentyTwentyFour.daySix

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun turnRight(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
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
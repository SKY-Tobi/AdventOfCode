package twentyTwentyFour.dayTwelve

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun getDeltas(): Pair<Int, Int> {
        return when (this) {
            NORTH -> Pair(0, -1)
            EAST -> Pair(1, 0)
            SOUTH -> Pair(0, 1)
            WEST -> Pair(-1, 0)
        }
    }
}
package twentyTwentyFour.dayFour

enum class Direction {
    TOP_LEFT, TOP, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT;

    fun getDeltas(): Pair<Int, Int> {
        return when (this) {
            TOP_LEFT -> Pair(-1, -1)
            TOP -> Pair(0, -1)
            TOP_RIGHT -> Pair(1, -1)
            RIGHT -> Pair(1, 0)
            BOTTOM_RIGHT -> Pair(1, 1)
            BOTTOM -> Pair(0, 1)
            BOTTOM_LEFT -> Pair(-1, 1)
            LEFT -> Pair(-1, 0)
        }
    }
}
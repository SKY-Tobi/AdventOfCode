package twentyTwentyFour.dayFifteen

data class Box(
    var leftSide: Pair<Int, Int>,
    var rightSide: Pair<Int, Int>,
) {
    fun canBoxBePushed(direction: Direction, map: MutableList<MutableList<Char>>, listOfBoxes: List<Box>): Boolean {
        when (direction) {
            Direction.WEST -> return canSideBePushed(leftSide, direction, map, listOfBoxes)
            Direction.EAST -> return canSideBePushed(rightSide, direction, map, listOfBoxes)
            else -> {}
        }
        return canSideBePushed(leftSide, direction, map, listOfBoxes) && canSideBePushed(
            rightSide,
            direction,
            map,
            listOfBoxes
        )
    }

    private fun canSideBePushed(
        side: Pair<Int, Int>,
        direction: Direction,
        map: MutableList<MutableList<Char>>,
        boxes: List<Box>
    ): Boolean {
        val (dx, dy) = direction.getDeltas()
        val newX = side.first + dx
        val newY = side.second + dy

        if (map[newY][newX] == '#') return false

        return boxes.none { it.contains(Pair(newX, newY)) } || boxes.first { it.contains(Pair(newX, newY)) }
            .canBoxBePushed(direction, map, boxes)
    }

    fun contains(position: Pair<Int, Int>): Boolean {
        return position == leftSide || position == rightSide
    }

    fun moveBox(direction: Direction, map: MutableList<MutableList<Char>>, boxes: MutableList<Box>) {
        val (dx, dy) = direction.getDeltas()
        val (newX, newY) = if (direction == Direction.EAST) {
            Pair(rightSide.first + dx, rightSide.second + dy)
        } else {
            Pair(leftSide.first + dx, leftSide.second + dy)
        }

        boxes.firstOrNull { it.contains(Pair(newX, newY)) }?.moveBox(direction, map, boxes)

        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            boxes.firstOrNull { it.contains(Pair(newX + 1, newY)) }?.moveBox(direction, map, boxes)
        }

        map[leftSide.second][leftSide.first] = '.'
        map[rightSide.second][rightSide.first] = '.'
        leftSide = if (direction != Direction.EAST) Pair(newX, newY) else Pair(newX - 1, newY)
        rightSide = if (direction != Direction.EAST) Pair(newX + 1, newY) else Pair(newX, newY)
        map[leftSide.second][leftSide.first] = '['
        map[rightSide.second][rightSide.first] = ']'
    }
}
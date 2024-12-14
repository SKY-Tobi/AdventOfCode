package twentyTwentyFour.dayTwelve

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val map = lines.map { it.toCharArray() }.toTypedArray()
            val areas = initializeAreas(map)
            val totalCost = areas.sumOf { calculateArea(it, map) }
            println("Total cost: $totalCost")
        }

        private fun initializeAreas(map: Array<CharArray>): List<Area> {
            val mapWithLeftOverPlants = map.map { it.map { char -> char as Char? }.toMutableList() }.toMutableList()
            val areas = mutableListOf<Area>()
            map.indices.forEach { y ->
                map[y].indices.forEach { x ->
                    mapWithLeftOverPlants[y][x]?.let {
                        val area = Area(it, mutableListOf(Pair(x, y)))
                        areas.add(area)
                        mapWithLeftOverPlants[y][x] = null
                        initializeArea(mapWithLeftOverPlants, area, x, y)
                    }
                }
            }
            return areas
        }

        private fun initializeArea(mapWithLeftOverPlants: MutableList<MutableList<Char?>>, area: Area, x: Int, y: Int) {
            Direction.values().forEach {
                val (dx, dy) = it.getDeltas()
                val x1 = x + dx
                val y1 = y + dy
                if (x1 in mapWithLeftOverPlants[y].indices && y1 in mapWithLeftOverPlants.indices) {
                    if (mapWithLeftOverPlants[y1][x1] == area.label) {
                        area.plantCoordinates.add(Pair(x1, y1))
                        mapWithLeftOverPlants[y1][x1] = null
                        initializeArea(mapWithLeftOverPlants, area, x1, y1)
                    }
                }
            }
        }

        private fun calculateArea(area: Area, map: Array<CharArray>): Int {
            val fenceMap = mutableMapOf<Char, MutableList<Triple<Int, Int, Direction>>>()
            area.plantCoordinates.forEach { (x, y) ->
                Direction.values().forEach { direction ->
                    val (dx, dy) = direction.getDeltas()
                    val x1 = x + dx
                    val y1 = y + dy
                    if (x1 !in map[y].indices || y1 !in map.indices || map[y1][x1] != area.label) {
                        when (direction) {
                            Direction.NORTH, Direction.SOUTH -> {
                                fenceMap.computeIfAbsent('-') { mutableListOf() }.add(Triple(x1, y1, direction))
                            }
                            Direction.EAST, Direction.WEST -> {
                                fenceMap.computeIfAbsent('|') { mutableListOf() }.add(Triple(x1, y1, direction))
                            }
                        }
                    }
                }
            }
            return area.plantCoordinates.size * calculateNumberOfSides(fenceMap)
        }

        private fun calculateNumberOfSides(fenceMap: MutableMap<Char, MutableList<Triple<Int, Int, Direction>>>): Int {
            val horizontalFences = fenceMap['-'] ?: mutableListOf()
            val verticalFences = fenceMap['|'] ?: mutableListOf()
            var numberOfSides = 0

            numberOfSides += countSides(horizontalFences, Direction.WEST, Direction.EAST)
            numberOfSides += countSides(verticalFences, Direction.NORTH, Direction.SOUTH)

            return numberOfSides
        }

        private fun countSides(fences: MutableList<Triple<Int, Int, Direction>>, dir1: Direction, dir2: Direction): Int {
            val unwalkedFences = fences.toMutableList()
            var numberOfSides = 0
            fences.forEach {
                if (it in unwalkedFences) {
                    walkTillBorder(dir1, it.first, it.second, unwalkedFences, it.third)
                    walkTillBorder(dir2, it.first, it.second, unwalkedFences, it.third)
                    unwalkedFences.remove(it)
                    numberOfSides++
                }
            }
            return numberOfSides
        }

        private fun walkTillBorder(
            direction: Direction,
            x: Int,
            y: Int,
            list: MutableList<Triple<Int, Int, Direction>>,
            directionFenceFaces: Direction
        ) {
            val (dx, dy) = direction.getDeltas()
            val x1 = x + dx
            val y1 = y + dy
            if (list.remove(Triple(x1, y1, directionFenceFaces))) {
                walkTillBorder(direction, x1, y1, list, directionFenceFaces)
            }
        }
    }
}
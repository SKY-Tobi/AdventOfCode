package twentyTwentyFour.dayTwelve

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val areas = initializeAreas(map)
            val totalCost = areas.sumOf { calculateArea(it, map) }
            println("Total cost: $totalCost")
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
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
                if (x1 >= 0 && x1 < mapWithLeftOverPlants[y].size && y1 >= 0 && y1 < mapWithLeftOverPlants.size) {
                    if (mapWithLeftOverPlants[y1][x1] == area.label) {
                        area.plantCoordinates.add(Pair(x1, y1))
                        mapWithLeftOverPlants[y1][x1] = null
                        initializeArea(mapWithLeftOverPlants, area, x1, y1)
                    }
                }
            }
        }

        private fun calculateArea(area: Area, map: Array<CharArray>): Int {
            val fenceMap = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
            area.plantCoordinates.forEach {
                val (x, y) = it
                Direction.values().forEach { direction ->
                    val (dx, dy) = direction.getDeltas()
                    val x1 = x + dx
                    val y1 = y + dy
                    if (x1 < 0 || x1 >= map[y].size || y1 < 0 || y1 >= map.size || map[y1][x1] != area.label) {
                        when (direction) {
                            Direction.NORTH, Direction.SOUTH -> {
                                fenceMap.computeIfAbsent('-') { mutableListOf() }.add(Pair(x1, y1))
                            }
                            Direction.EAST, Direction.WEST -> {
                                fenceMap.computeIfAbsent('|') { mutableListOf() }.add(Pair(x1, y1))
                            }
                        }
                    }
                }
            }
            val numberOfSides = calculateNumberOfSides(fenceMap)
            println("Area ${area.label}: ${area.plantCoordinates.size} plants, $numberOfSides sides")

            return area.plantCoordinates.size * numberOfSides
        }

        private fun calculateNumberOfSides(fenceMap: MutableMap<Char, MutableList<Pair<Int, Int>>>): Int {
            val horizontalFences = fenceMap['-'] ?: mutableListOf()
            val verticalFences = fenceMap['|'] ?: mutableListOf()
            var numberOfSides = 0

            val unwalkedHorizontalFences = horizontalFences.toMutableList()
            horizontalFences.forEach {
                if(!unwalkedHorizontalFences.contains(it)) return@forEach

                val walkedCoordinates = mutableListOf<Pair<Int, Int>>()

                walkTillBorder(Direction.WEST, it.first, it.second, unwalkedHorizontalFences, walkedCoordinates)
                walkTillBorder(Direction.EAST, it.first, it.second, unwalkedHorizontalFences, walkedCoordinates)

                unwalkedHorizontalFences.remove(it)
                numberOfSides++
            }

            val unwalkedVerticalFences = verticalFences.toMutableList()
            verticalFences.forEach {
                if(!unwalkedVerticalFences.contains(it)) return@forEach

                val walkedCoordinates = mutableListOf<Pair<Int, Int>>()

                walkTillBorder(Direction.NORTH, it.first, it.second, unwalkedVerticalFences, walkedCoordinates)
                walkTillBorder(Direction.SOUTH, it.first, it.second, unwalkedVerticalFences, walkedCoordinates)

                unwalkedVerticalFences.remove(it)
                numberOfSides++
            }

            return numberOfSides
        }

        private fun walkTillBorder(direction: Direction, x: Int, y: Int, list: MutableList<Pair<Int, Int>>, walkedCoordinates: MutableList<Pair<Int, Int>>) {
            val (dx, dy) = direction.getDeltas()
            val x1 = x + dx
            val y1 = y + dy
            while (list.contains(Pair(x1, y1)) && !walkedCoordinates.contains(Pair(x1, y1))) {
                if(!walkedCoordinates.contains(Pair(x1, y1))) {
                    list.removeAt(list.indexOf(Pair(x1, y1))) // only remove one element as there can be multiple elements with the same coordinates
                    walkedCoordinates.add(Pair(x1, y1))
                }
                walkTillBorder(direction, x1, y1, list, walkedCoordinates)
            }
        }
    }
}
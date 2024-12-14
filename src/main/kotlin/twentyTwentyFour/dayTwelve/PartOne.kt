package twentyTwentyFour.dayTwelve

class PartOne {
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
            val regionArea = area.plantCoordinates.size
            val perimeters = area.plantCoordinates.sumOf {
                var sum = 0
                val (x, y) = it
                Direction.values().forEach { direction ->
                    val (dx, dy) = direction.getDeltas()
                    val x1 = x + dx
                    val y1 = y + dy
                    if (x1 < 0 || x1 >= map[y].size || y1 < 0 || y1 >= map.size || map[y1][x1] != area.label) {
                        sum++
                    }
                }
                sum
            }
            return regionArea * perimeters
        }
    }
}
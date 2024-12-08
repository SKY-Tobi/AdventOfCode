package twentyTwentyFour.dayEight

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val antennas = calculateAntennas(map)
            calculateAntinodes(map, antennas).forEach { map[it.second][it.first] = 'X' }
            println("amount of antinodes: " + map.sumOf { row -> row.count { it != '.' } })
        }

        private fun initializeMap(input: List<String>): Array<CharArray> {
            return input.map { it.toCharArray() }.toTypedArray()
        }

        private fun calculateAntennas(map: Array<CharArray>): Map<Pair<Int, Int>, String> {
            val antennas = mutableMapOf<Pair<Int, Int>, String>()
            map.forEachIndexed { y, row ->
                row.forEachIndexed { x, antenna ->
                    if(antenna.toString() != ".") antennas[Pair(x, y)] = antenna.toString()
                }
            }
            return antennas
        }

        private fun calculateAntinodes(map: Array<CharArray>, antennas: Map<Pair<Int, Int>, String>): List<Pair<Int, Int>> {
            val result = mutableSetOf<Pair<Int, Int>>()
            antennas.forEach { (position, antenna) ->
                val x = position.first
                val y = position.second
                antennas.filter { it.value == antenna && it.key != Pair(x, y) }.forEach { (otherPosition, _) ->
                    var firstSideOutOfMap = false
                    var otherSideOutOfMap = false
                    var multiplier = 1
                    while(true) {
                        val firstSide = pointReflection(position, otherPosition, multiplier)
                        val otherSide = pointReflection(otherPosition, position, multiplier)
                        if(firstSide.first < 0 || firstSide.first >= map[0].size || firstSide.second < 0 || firstSide.second >= map.size) {
                            firstSideOutOfMap = true
                        }
                        if(otherSide.first < 0 || otherSide.first >= map[0].size || otherSide.second < 0 || otherSide.second >= map.size) {
                            otherSideOutOfMap = true
                        }
                        if(firstSideOutOfMap && otherSideOutOfMap) {
                            break
                        }
                        if(!firstSideOutOfMap && map[firstSide.second][firstSide.first] == '.') {
                            result.add(firstSide)
                        }
                        if(!otherSideOutOfMap && map[otherSide.second][otherSide.first] == '.') {
                            result.add(otherSide)
                        }
                        multiplier++
                    }
                }
            }
            return result.filter { it.first >= 0 && it.first < map[0].size && it.second >= 0 && it.second < map.size }
        }

        private fun pointReflection(center: Pair<Int, Int>, point: Pair<Int, Int>, multiplier: Int): Pair<Int, Int> {
            val dx = point.first - center.first
            val dy = point.second - center.second
            return Pair(center.first - multiplier * dx, center.second - multiplier * dy)
        }
    }
}
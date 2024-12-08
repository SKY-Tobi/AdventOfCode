package twentyTwentyFour.dayEight

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val antennas = calculateAntennas(map)
            val antinodes = calculateAntinodes(map, antennas)
            println("amount of antinodes: " + antinodes.size)
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
                    result.add(pointReflection(position, otherPosition))
                    result.add(pointReflection(otherPosition, position))
                }
            }
            return result.filter { it.first >= 0 && it.first < map[0].size && it.second >= 0 && it.second < map.size }
        }

        private fun pointReflection(center: Pair<Int, Int>, point: Pair<Int, Int>): Pair<Int, Int> {
            val dx = point.first - center.first
            val dy = point.second - center.second
            return Pair(center.first - dx, center.second - dy)
        }
    }
}
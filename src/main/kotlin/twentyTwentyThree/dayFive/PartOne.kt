package twentyTwentyThree.dayFive

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val seeds = evaluateSeeds(lines[0])
            val maps = evaluateMaps(lines)
            val locations = mutableListOf<Long>()
            seeds.forEach {
                var currentIndex = it
                maps.forEach map@ { map ->
                    map.value.forEach { seedMap ->
                        if(seedMap.source <= currentIndex && currentIndex <= seedMap.source + seedMap.range) {
                            currentIndex = seedMap.destination + (seedMap.source - currentIndex) * -1
                            return@map
                        }
                    }
                }
                locations.add(currentIndex)
            }
            locations.sort()
            println("smallest location found: " + locations[0])
        }

        private fun evaluateSeeds(seedStream: String): List<Long> {
            return seedStream.split(" ").filter { it.toLongOrNull() != null }.map { it.toLong() }
        }

        private fun evaluateMaps(lines: List<String>): Map<String, List<SeedMap>> {
            val seedMap = mutableMapOf<String, List<SeedMap>>()
            var currentSeedMapKey = ""
            var currentSeedMap = mutableListOf<SeedMap>()
            mutableListOf(lines).removeAt(0).forEach {
                if(it.isEmpty()) return@forEach
                val input = it.split(" ")
                if(input[0].toLongOrNull() == null) {
                    if(currentSeedMap.isNotEmpty()) {
                        seedMap[currentSeedMapKey] = currentSeedMap
                    }
                    currentSeedMapKey = input[0]
                    currentSeedMap = mutableListOf()
                } else {
                    currentSeedMap.add(SeedMap(input[0].toLong(), input[1].toLong(), input[2].toLong()))
                }
            }
            seedMap[currentSeedMapKey] = currentSeedMap
            return seedMap
        }
    }
}

package twentyTwentyThree.dayFive

class PartTwo {
    companion object {

        //TODO Nur Seeds mappen, welche in der Range sind, sonst belassen...
        fun execute(lines: List<String>) {
            val maps = evaluateMaps(lines)
            val seeds = evaluateSeeds(lines[0])
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
            val seedPairs = seedStream.split(" ").filter { it.toLongOrNull() != null }.map { it.toLong() }
            val seeds = mutableListOf<Long>()
            for(x in seedPairs.indices step 2) {
                repeat(seedPairs[x + 1].toInt()) { index ->
                    seeds.add(seedPairs[x] + index)
                }
            }
            return seeds
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

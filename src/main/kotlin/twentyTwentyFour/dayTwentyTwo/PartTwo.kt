package twentyTwentyFour.dayTwentyTwo

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val initialSecrets = lines.map { it.toLong() }
            val allPriceChanges = initialSecrets.map { generatePriceChanges(it) }
            val bestSequence = findBestSequence(allPriceChanges)
            println(bestSequence.second)
        }

        fun nextSecret(secret: Long): Long {
            var newSecret = secret
            newSecret = mixAndPrune(newSecret * 64, newSecret)
            newSecret = mixAndPrune(newSecret / 32, newSecret)
            newSecret = mixAndPrune(newSecret * 2048, newSecret)
            return newSecret
        }

        fun mixAndPrune(value: Long, secret: Long): Long {
            return (value xor secret) % 16777216
        }

        fun generatePriceChanges(initialSecret: Long): List<Pair<Int, Int>> {
            val prices = mutableListOf<Int>()
            var secret = initialSecret
            repeat(2001) {
                prices.add((secret % 10).toInt())
                secret = nextSecret(secret)
            }
            return prices.zipWithNext { a, b -> b - a to b }
        }

        fun findBestSequence(allPriceChanges: List<List<Pair<Int, Int>>>): Pair<List<Int>, Int> {
            val sequences = mutableMapOf<List<Int>, Int>()
            for (priceChanges in allPriceChanges) {
                val occurredSequences = mutableMapOf<List<Int>, Boolean>()
                for (i in 4 until priceChanges.size) {
                    val sequence = priceChanges.subList(i - 3, i+1).map { it.first }
                    if (occurredSequences.getOrDefault(sequence, false)) {
                        continue
                    }
                    sequences[sequence] = sequences.getOrDefault(sequence, 0) + priceChanges[i].second
                    occurredSequences[sequence] = true
                }
            }
            return sequences.maxByOrNull { it.value }?.toPair() ?: (emptyList<Int>() to 0)
        }
    }
}
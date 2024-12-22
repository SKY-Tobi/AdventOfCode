package twentyTwentyFour.dayTwentyTwo

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            val initialSecrets = lines.map { it.toLong() }
            val result = initialSecrets.sumOf { generate2000thSecret(it) }
            println(result)
        }

        fun generate2000thSecret(initialSecret: Long): Long {
            var secret = initialSecret
            repeat(2000) {
                secret = nextSecret(secret)
            }
            return secret
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
    }
}
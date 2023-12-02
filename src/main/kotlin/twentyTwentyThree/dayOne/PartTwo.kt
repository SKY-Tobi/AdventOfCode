package twentyTwentyThree.dayOne

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            var sum = 0
            lines.forEach {
                val digits = evaluateDigits(it)
                sum += Integer.parseInt(digits[0]) * 10 + Integer.parseInt(digits[digits.size - 1])
            }
            println("calibration value: $sum")
        }

        private fun evaluateDigits(line: String) : List<String> {
            val validDigits = HashMap<String, String>()
            validDigits["1"] = "1"
            validDigits["2"] = "2"
            validDigits["3"] = "3"
            validDigits["4"] = "4"
            validDigits["5"] = "5"
            validDigits["6"] = "6"
            validDigits["7"] = "7"
            validDigits["8"] = "8"
            validDigits["9"] = "9"
            validDigits["one"] = "1"
            validDigits["two"] = "2"
            validDigits["three"] = "3"
            validDigits["four"] = "4"
            validDigits["five"] = "5"
            validDigits["six"] = "6"
            validDigits["seven"] = "7"
            validDigits["eight"] = "8"
            validDigits["nine"] = "9"

            val result = mutableListOf<String>()
            var lowestIndex = Int.MAX_VALUE
            var lowestIndexValue = ""
            var highestIndex = Int.MIN_VALUE
            var highestIndexValue = ""

            validDigits.entries.forEach {
                val lowestFoundIndex = line.indexOf(it.key)
                if (lowestFoundIndex > -1 && lowestIndex > lowestFoundIndex) {
                    lowestIndex = lowestFoundIndex
                    lowestIndexValue = it.value
                }

                val highestFoundIndex = line.lastIndexOf(it.key)
                if (highestFoundIndex > -1 && highestFoundIndex > highestIndex) {
                    highestIndex = highestFoundIndex
                    highestIndexValue = it.value
                }
            }

            result.add(lowestIndexValue)
            result.add(highestIndexValue)

            return result
        }
    }
}

package dayEleven

class Monkey(
    var startingItems: ArrayList<Long> = arrayListOf(),
    var operationOperator: Char = '?',
    var operationParameter: String = "",
    var testParameter: Int = 0,
    var testPositiveMonkeyNumber: Int = 0,
    var testNegativeMonkeyNumber: Int = 0,
    var inspects: Int = 0
) {
    fun operation(old: Long): Long {
        return if (operationParameter == "old") {
            old * old
        } else {
            return when (operationOperator) {
                '+' -> old + operationParameter.toInt()
                '-' -> old - operationParameter.toInt()
                '*' -> old * operationParameter.toInt()
                '/' -> old / operationParameter.toInt()
                else -> throw Exception("invalid OperationOperator")
            }
        }
    }

    fun test(input: Long): Boolean {
        return input % testParameter == 0L
    }
}
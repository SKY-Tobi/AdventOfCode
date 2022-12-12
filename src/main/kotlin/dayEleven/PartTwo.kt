package dayEleven

class PartTwo {
    companion object {
        private const val ROUNDS = 10000

        fun execute(lines: List<String>) {
            val monkeys = initializeMonkeys(lines)
            val lcm = getKGV(monkeys.map { it.testParameter })
            repeat(ROUNDS) {
                monkeys.forEach { monkey ->
                    var worryLevel: Long
                    monkey.startingItems.forEach {
                        worryLevel = monkey.operation(it)
                        worryLevel %= lcm
                        if (monkey.test(worryLevel)) {
                            monkeys[monkey.testPositiveMonkeyNumber].startingItems.add(worryLevel)
                        } else {
                            monkeys[monkey.testNegativeMonkeyNumber].startingItems.add(worryLevel)
                        }
                        monkey.inspects++
                    }
                    monkey.startingItems = arrayListOf()
                }
            }
            monkeys.forEachIndexed { index, it ->
                println("Monkey $index inspected items ${it.inspects} times:")
            }

            val highestTwoInspectations = monkeys.sortedByDescending { it.inspects }.take(2).map { it.inspects }
            println("monkey business = ${highestTwoInspectations[0]} * ${highestTwoInspectations[1]} = " + (highestTwoInspectations[0].toLong() * highestTwoInspectations[1].toLong()))
        }

        private fun initializeMonkeys(lines: List<String>): ArrayList<Monkey> {
            val monkeys = arrayListOf<Monkey>()
            var nextTask = arrayListOf("monkeyNumber", "startingItems", "operation", "test", "testTrue", "testFalse")
            var tempMonkey = Monkey()
            lines.stream().forEach { it ->
                if (it.isEmpty()) {
                    return@forEach
                }
                val pattern = "\\d+".toRegex()
                val input = pattern.findAll(it).toList()
                when (nextTask[0]) {
                    "monkeyNumber" -> {}
                    "startingItems" -> {
                        input.forEach { tempMonkey.startingItems.add(it.value.toLong()) }
                    }

                    "operation" -> {
                        // operationInput[5] = operator, operationInput[6] = parameter
                        val operationInput = it.split("\\s+".toRegex())
                        tempMonkey.operationOperator = operationInput[5].toCharArray()[0]
                        tempMonkey.operationParameter = operationInput[6]
                    }

                    "test" -> {
                        tempMonkey.testParameter = input[0].value.toInt()
                    }

                    "testTrue" -> {
                        tempMonkey.testPositiveMonkeyNumber = input[0].value.toInt()
                    }

                    "testFalse" -> {
                        tempMonkey.testNegativeMonkeyNumber = input[0].value.toInt()
                    }

                    else -> throw Exception("invalid task")
                }
                nextTask.removeFirst()

                if (nextTask.isEmpty()) {
                    monkeys.add(tempMonkey)
                    tempMonkey = Monkey()
                    nextTask =
                        arrayListOf("monkeyNumber", "startingItems", "operation", "test", "testTrue", "testFalse")
                }
            }
            return monkeys
        }

        // Kleinstes Gemeinsames Vielfach
        fun getKGV(list: List<Int>): Int {
            var myResult: Int = list.max()

            while (true) {
                if (list.all { myResult % it == 0 }) {
                    return myResult
                }
                ++myResult
            }
        }
    }
}

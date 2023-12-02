package twentyTwentyTwo.dayOne

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val elvesInventory = mutableListOf<Int>()
            var tempInventory = 0
            lines.forEach {
                if (it.isNotEmpty()) {
                    tempInventory += Integer.parseInt(it)
                } else {
                    elvesInventory.add(tempInventory)
                    tempInventory = 0
                }
            }
            elvesInventory.sortByDescending { it }
            val highestThreeCaloryInventories = elvesInventory.take(3)
            print("Elves 3 highest Calorie inventories: " + highestThreeCaloryInventories.sum())
            println()
        }
    }
}

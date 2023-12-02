package twentyTwentyTwo.dayOne

class PartOne {
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
            println("Elf with highest Calories: " + elvesInventory.max())
        }
    }
}

package twentyTwentyThree.dayThree

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val map = initializeMap(lines)
            val gears: MutableMap<Pair<Int, Int>, Pair<String, String>> = mutableMapOf()

            // loop of every row
            lines.forEachIndexed { indexY, it ->
                var indexXOfCurrentNumber = 0
                var numberLength = 0
                var isNewNumber = true
                var currentNumber = ""

                // loop of every char per row
                it.toCharArray().forEachIndexed { indexX, symbol ->
                    if (isNewNumber && symbol.isDigit()) {
                        indexXOfCurrentNumber = indexX
                        currentNumber += symbol
                        numberLength++
                        isNewNumber = false
                    } else if (symbol.isDigit()) {
                        currentNumber += symbol
                        numberLength++
                    } else if (!isNewNumber) {
                        val foundGear = isAdjacentToGear(map, indexXOfCurrentNumber, indexY, numberLength)
                        if (foundGear != null) {
                            val gear = gears[foundGear]
                            if(gear == null) {
                                gears[foundGear] = Pair(currentNumber, "")
                            } else {
                                gears[foundGear] = Pair(gear.first, currentNumber)
                            }
                        }
                        isNewNumber = true
                        currentNumber = ""
                        numberLength = 0
                    }
                }

                if(currentNumber.isNotEmpty()) {
                    val foundGear = isAdjacentToGear(map, indexXOfCurrentNumber, indexY, numberLength)
                    if (foundGear != null) {
                        val gear = gears[foundGear]
                        if(gear == null) {
                            gears[foundGear] = Pair(currentNumber, "")
                        } else {
                            gears[foundGear] = Pair(gear.first, currentNumber)
                        }
                    }
                    isNewNumber = true
                    currentNumber = ""
                    numberLength = 0
                }
            }

            var sum = 0
            gears.forEach {
                if(it.value.first.isNotEmpty() && it.value.second.isNotEmpty()) {
                    sum += Integer.parseInt(it.value.first) * Integer.parseInt(it.value.second)
                }
            }

            println("sum of gear ratios: $sum")
        }

        private fun isAdjacentToGear(
            map: Array<CharArray>,
            indexXOfCurrentNumber: Int,
            indexYOfCurrentNumber: Int,
            numberLength: Int
        ): Pair<Int, Int>? {
            val width = map[0].size
            val height = map.size
            val fieldsToCheck = arrayListOf<Pair<Int, Int>>()
            for(y in indexYOfCurrentNumber - 1 .. indexYOfCurrentNumber + 1) {
                for(x in indexXOfCurrentNumber - 1 .. indexXOfCurrentNumber + numberLength) {
                    // check on the other side of the grid
                    /*
                    var actualYToCheck = y
                    var actualXToCheck = x

                    if(x < 0) {
                        actualXToCheck = width + x
                    }

                    if(x >= width) {
                        actualXToCheck = x - width
                    }

                    if(y < 0) {
                        actualYToCheck = height + y
                    }

                    if(y >= height) {
                        actualYToCheck = y - height
                    }

                    fieldsToCheck.add(Pair(actualYToCheck, actualXToCheck))
                    */

                    // only valid fields within the grid
                    if(y in 0 until height && x in 0 until width) {
                        fieldsToCheck.add(Pair(y, x))
                    }
                }
            }

            val regexNotPoint = Regex("\\*")
            fieldsToCheck.forEach {
                if(regexNotPoint.matches(map[it.first][it.second].toString())){
                    return it
                }
            }
            return null
        }

        private fun initializeMap(lines: List<String>): Array<CharArray> {
            val width = lines[0].length
            val height = lines.size
            val map = Array(height) { CharArray(width) }
            lines.forEachIndexed { indexY, it ->
                val line = it.toCharArray()
                line.forEachIndexed { indexX, symbol ->
                    map[indexY][indexX] = symbol
                }
            }
            return map
        }
    }
}

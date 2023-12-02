package twentyTwenty.dayFive

import java.util.*
import java.util.regex.Pattern
import kotlin.system.exitProcess

class PartOne {
    companion object {
        private const val SHIP_HEIGHT = 40
        private const val EMPTY_CONTAINER = "    "
        fun execute(lines: List<String>) {
            val ship = analyseShipSize(lines)
            val cargo = ship.cargo
            var hasFieldBeenInitialized = false
            lines.forEachIndexed { index, it ->
                if (!hasFieldBeenInitialized) {
                    try {
                        // check if line only contains numbers
                        it.replace("\\s".toRegex(), "").toInt()
                        hasFieldBeenInitialized = true
                    } catch (ignored: Exception) {
                        val p = Pattern.compile("\\s{4}|(\\[[A-Z]])")
                        val m = p.matcher(it)
                        var i = 0
                        while (m.find()) {
                            if (m.group().trim { it <= ' ' }.isNotEmpty()) {
                                cargo[index + SHIP_HEIGHT - ship.initializeHighestPoint][i] = m.group() + " "
                            }
                            i++
                        }
                    }
                } else {
                    //get input (amount to move, fromColumn and toColumn) (3 Input parameters else skip)
                    val splitInput: MutableList<Int> = ArrayList()
                    val p = Pattern.compile("\\d+")
                    val m = p.matcher(it)
                    while (m.find()) {
                        splitInput.add(m.group().toInt())
                    }
                    if (splitInput.size == 3) {
                        val amount = splitInput[0]
                        val fromX = splitInput[1] - 1
                        val toX = splitInput[2] - 1
                        val tempCrane: MutableList<String?> = ArrayList()
                        var amountLeftToMove = amount
                        run {
                            var y = 0
                            while (amountLeftToMove != 0 && y < SHIP_HEIGHT) {
                                if (cargo[y][fromX]!!.trim().isNotEmpty()) {
                                    tempCrane.add(cargo[y][fromX])
                                    cargo[y][fromX] = EMPTY_CONTAINER
                                    amountLeftToMove--
                                }
                                y++
                            }
                        }
                        //find Index where container starts (when column is empty, then SHIP_HEIGHT + 1)
                        var highestContainerIndex = SHIP_HEIGHT
                        var toY = 0
                        while (highestContainerIndex == SHIP_HEIGHT && toY < SHIP_HEIGHT) {
                            if (cargo[toY][toX]!!.trim().isNotEmpty()) {
                                highestContainerIndex = toY
                            }
                            toY++
                        }
                        try {
                            //start filling column to the top till no more container are left to move
                            for (y in highestContainerIndex - 1 downTo highestContainerIndex - 1 - tempCrane.size + 1) {
                                cargo[y][toX] = tempCrane[highestContainerIndex - 1 - y]
                            }
                        } catch (e: Exception) {
                            System.err.println("amount: $amount, fromX: $fromX, toX $toX")
                            exitProcess(4)
                        }
                    } else {
                        println("line skipped: $index")
                    }
                }
            }
            println("End State of Cargos: ")
            for (row in cargo) println(row.contentToString())
            println()
            //Get Highest Container Letter
            val stringBuilder = StringBuilder()
            for (x in 0 until ship.cargoColumns) {
                for (y in 0 until SHIP_HEIGHT) {
                    if (cargo[y][x]!!.trim().isNotEmpty()) {
                        val p = Pattern.compile("[A-Z]")
                        val m = p.matcher(cargo[y][x]!!)
                        if (m.find()) {
                            stringBuilder.append(m.group())
                            break
                        }
                    }
                }
            }
            println("All letters of highest Container of a column combined: $stringBuilder")
        }

        private fun analyseShipSize(lines: List<String>): Ship {
            val ship = Ship()
            // analyse amount of columns by finding the row with column labeling (1 2 3 ...) which only contains numbers
            var x = 0
            var y = 0
            for (line in lines) {
                try {
                    // check if line only contains numbers
                    line.replace("\\s".toRegex(), "").toInt()
                    // pulls all numbers out and saves the last one which should be the highest
                    val numbers = line.replace("\\s".toRegex(), "")
                    x = numbers.substring(numbers.length - 1).toInt()
                    ship.cargoColumns = x
                    ship.initializeHighestPoint = y
                    break
                } catch (ignored: Exception) {
                    y++
                }
            }
            ship.cargo = Array(SHIP_HEIGHT) { arrayOfNulls(x) }
            for (array in ship.cargo) Arrays.fill(array, "    ")
            return ship
        }
    }
}
package twentyTwentyThree.dayTen

class PartTwo {
    companion object {
        fun execute(lines: List<String>) {
            val pipeMap = evaluateMap(lines)
            val loop = evaluateLoop(
                PipeNode(null, null, null, null, 0, evaluateEmptyMap(lines)),
                pipeMap,
                pipeMap.startLocation.first,
                pipeMap.startLocation.second,
                0,
                Pair(pipeMap.startLocation.first, pipeMap.startLocation.second),
                null
            )

            loop?.map?.forEach {
                println(it)
            }
            println("amount of steps needed to reach the furthest distance away from starting position : ")
        }

        private fun evaluateEmptyMap(lines: List<String>): Array<CharArray> {
            val width = lines[0].toCharArray().size
            val height = lines.size
            val map = Array(width) { CharArray(height) }
            for (y in 0 until height) {
                for (x in 0 until width) {
                    map[y][x] = '.'
                }
            }
            return map
        }

        private fun evaluateMap(lines: List<String>): PipeMap {
            val width = lines[0].toCharArray().size
            val height = lines.size
            val pipeMap = Array(width) { CharArray(height) }
            var startLocation: Pair<Int, Int> = Pair(0, 0)
            for (y in 0 until height) {
                val line = lines[y].toCharArray()
                for (x in 0 until width) {
                    if (line[x] == 'S') {
                        startLocation = Pair(y, x)
                    }
                    pipeMap[y][x] = line[x]
                }
            }
            return PipeMap(startLocation, pipeMap)
        }

        private tailrec fun evaluateLoop(
            currentNode: PipeNode,
            pipeMap: PipeMap,
            y: Int,
            x: Int,
            index: Int,
            previousPosition: Pair<Int, Int>,
            cardinalDirection: CardinalDirection?
        ): PipeNode? {
            val map = pipeMap.map
            when (cardinalDirection) {
                CardinalDirection.NORTH -> {
                    //North check
                    if (previousPosition.first != y - 1 && y - 1 >= 0) {
                        if (isLoopFinished(pipeMap, y - 1, x)) {
                            currentNode.map!![y - 1][x] = 'S'
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return currentNode
                        }

                        val nextCardinalDirection = evaluateValidPipeFromSouth(map[y - 1][x])
                        if (nextCardinalDirection != null) {
                            currentNode.map!![y - 1][x] = 'A'
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.north!!,
                                pipeMap,
                                y - 1,
                                x,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }
                }

                CardinalDirection.EAST -> {
                    //East check
                    if (previousPosition.second != x + 1 && x + 1 < map[0].size) {
                        if (isLoopFinished(pipeMap, y, x + 1)) {
                            currentNode.map!![y][x + 1] = 'S'
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return currentNode
                        }

                        val nextCardinalDirection = evaluateValidPipeFromWest(map[y][x + 1])
                        if (nextCardinalDirection != null) {
                            currentNode.map!![y][x + 1] = 'A'
                            currentNode.east = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.east!!,
                                pipeMap,
                                y,
                                x + 1,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }
                }

                CardinalDirection.SOUTH -> {
                    //South check
                    if (previousPosition.first != y + 1 && y + 1 < map.size) {
                        if (isLoopFinished(pipeMap, y + 1, x)) {
                            currentNode.map!![y + 1][x] = 'S'
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return currentNode
                        }

                        val nextCardinalDirection = evaluateValidPipeFromNorth(map[y + 1][x])
                        if (nextCardinalDirection != null) {
                            currentNode.map!![y + 1][x] = 'A'
                            currentNode.south = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.south!!,
                                pipeMap,
                                y + 1,
                                x,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }
                }

                CardinalDirection.WEST -> {
                    //West check
                    if (previousPosition.second != x - 1 && x - 1 >= 0) {
                        if (isLoopFinished(pipeMap, y, x - 1)) {
                            currentNode.map!![y][x - 1] = 'S'
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return currentNode
                        }

                        val nextCardinalDirection = evaluateValidPipeFromEast(map[y][x - 1])
                        if (nextCardinalDirection != null) {
                            currentNode.map!![y][x - 1] = 'A'
                            currentNode.west = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.west!!,
                                pipeMap,
                                y,
                                x - 1,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }
                }

                null -> {
                    //North check
                    if (previousPosition.first != y - 1 && y - 1 >= 0) {
                        val nextCardinalDirection = evaluateValidPipeFromSouth(map[y - 1][x])
                        if (nextCardinalDirection != null) {
                            currentNode.north = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.north!!,
                                pipeMap,
                                y - 1,
                                x,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }

                    //East check
                    if (previousPosition.second != x + 1 && x + 1 < map[0].size) {
                        val nextCardinalDirection = evaluateValidPipeFromWest(map[y][x + 1])
                        if (nextCardinalDirection != null) {
                            currentNode.east = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.east!!,
                                pipeMap,
                                y,
                                x + 1,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }

                    //South check
                    if (previousPosition.first != y + 1 && y + 1 < map.size) {
                        val nextCardinalDirection = evaluateValidPipeFromNorth(map[y + 1][x])
                        if (nextCardinalDirection != null) {
                            currentNode.south = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.south!!,
                                pipeMap,
                                y + 1,
                                x,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }

                    //West check
                    if (previousPosition.second != x - 1 && x - 1 >= 0) {
                        val nextCardinalDirection = evaluateValidPipeFromEast(map[y][x - 1])
                        if (nextCardinalDirection != null) {
                            currentNode.west = PipeNode(null, null, null, null, index + 1, currentNode.map)
                            return evaluateLoop(
                                currentNode.west!!,
                                pipeMap,
                                y,
                                x - 1,
                                index + 1,
                                Pair(y, x),
                                nextCardinalDirection
                            )
                        }
                    }
                }
            }
            return null
        }

        private fun evaluateValidPipeFromSouth(pipeType: Char): CardinalDirection? {
            return when (pipeType) {
                Pipe.SOUTH_WEST_BEND_PIPE.respresents -> CardinalDirection.WEST
                Pipe.SOUTH_EAST_BEND_PIPE.respresents -> CardinalDirection.EAST
                Pipe.VERTICAL_PIPE.respresents -> CardinalDirection.NORTH
                else -> null
            }
        }

        private fun evaluateValidPipeFromWest(pipeType: Char): CardinalDirection? {
            return when (pipeType) {
                Pipe.NORTH_WEST_BEND_PIPE.respresents -> CardinalDirection.NORTH
                Pipe.SOUTH_WEST_BEND_PIPE.respresents -> CardinalDirection.SOUTH
                Pipe.HORIZONTAL_PIPE.respresents -> CardinalDirection.EAST
                else -> null
            }
        }

        private fun evaluateValidPipeFromNorth(pipeType: Char): CardinalDirection? {
            return when (pipeType) {
                Pipe.NORTH_EAST_BEND_PIPE.respresents -> CardinalDirection.EAST
                Pipe.NORTH_WEST_BEND_PIPE.respresents -> CardinalDirection.WEST
                Pipe.VERTICAL_PIPE.respresents -> CardinalDirection.SOUTH
                else -> null
            }
        }

        private fun evaluateValidPipeFromEast(pipeType: Char): CardinalDirection? {
            return when (pipeType) {
                Pipe.SOUTH_EAST_BEND_PIPE.respresents -> CardinalDirection.SOUTH
                Pipe.NORTH_EAST_BEND_PIPE.respresents -> CardinalDirection.NORTH
                Pipe.HORIZONTAL_PIPE.respresents -> CardinalDirection.WEST
                else -> null
            }
        }

        private fun isLoopFinished(pipeMap: PipeMap, y: Int, x: Int): Boolean {
            return pipeMap.startLocation.first == y && pipeMap.startLocation.second == x
        }
    }
}

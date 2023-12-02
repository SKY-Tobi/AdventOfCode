package twentyTwenty.daySeven

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var homeNode: Node? = null
            var pointer: Node? = null
            lines.stream().forEach {
                val input = it.split(" ")
                if (homeNode == null) {
                    if (input[0] == "$" && input[1] == "cd" && input[2].isNotEmpty()) {
                        homeNode = Node(input[2])
                        pointer = homeNode
                    }
                } else {
                    //continue if it is a ls command
                    if (input[0] == "$" && input[1] == "ls") {
                        return@forEach
                    }

                    //change directory
                    if (input[0] == "$" && input[1] == "cd") {
                        // go directory up
                        pointer = if (input[2] == "..") {
                            pointer?.parent
                        } else {
                            pointer?.getNodeByName(input[2])
                        }
                        return@forEach
                    }

                    when (input[0]) {
                        "dir" -> pointer?.link(Node(input[1], pointer))
                        else -> pointer?.link(Node(input[1], pointer, input[0].toInt()))
                    }
                }
            }
            println(homeNode.toString())
            println()

            val fileCounter = FileCounter()
            homeNode?.countDirectoryWithTotalSizeSmaller100000(fileCounter)
            println(fileCounter.total)
        }
    }
}

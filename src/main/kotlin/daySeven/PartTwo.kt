package daySeven

class PartTwo {
    companion object {
        private const val totalDiskSpace = 70000000
        private const val spaceNeededForUpdate = 30000000

        fun execute(lines: List<String>) {
            var homeNode: Node? = null
            var pointer: Node? = null
            lines.stream().forEach {
                val input = it.split(" ")
                if (homeNode == null) {
                    if (input[0] == "$" && input[1] == "cd" && input[2].isNotEmpty()) {
                        homeNode = Node(input[2], null)
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
            println(homeNode?.getSpaceUsed(0).toString() + " space used of " + totalDiskSpace)
            println((totalDiskSpace - homeNode?.getSpaceUsed(0)!!).toString() + " out of " + spaceNeededForUpdate + " available for update")
            val spaceToDelete = spaceNeededForUpdate - (totalDiskSpace - homeNode?.getSpaceUsed(0)!!)
            val fileCounter = FileCounter()
            fileCounter.spaceToDelete = spaceToDelete.toLong()
            println("Size of Folder to delete: " + homeNode?.collectAllDirectoryNodesWithItsSize(fileCounter)?.nodes?.filter { it.size >= spaceToDelete }
                ?.sortedBy { it.size }?.get(0)?.size)
        }
    }
}

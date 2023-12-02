package twentyTwentyTwo.daySeven

class Node(
    private val name: String,
    val parent: Node? = null,
    var size: Int = 0,
    private var rightNode: ArrayList<Node> = arrayListOf(),
    private var depth: Int = 0,
) {

    fun link(right: Node) = this.apply {
        rightNode.add(right)
        right.depth(this.depth + 1)
    }

    private fun depth(value: Int) = this.apply { depth = value }

    fun size(value: Int) = this.apply { size = value }

    fun getNodeByName(name: String) = rightNode.find { it.name == name }

    override fun toString(): String {
        return StringBuffer().apply {
            for (i in 1..depth) {
                append("  ")
            }
            if (rightNode.isNotEmpty()) {
                append("- $name (dir)")
                rightNode.forEach { append("\n${it}") }
            } else {
                append("- $name (file, size=$size)")
            }
        }.toString()
    }

    fun countDirectoryWithTotalSizeSmaller100000(fileCounter: FileCounter): Int {
        fileCounter.counter = 0
        // if isNotEmpty = directory
        if (rightNode.isNotEmpty()) {
            var folderSize = 0
            rightNode.forEach {
                // if sub-node is another folder then call its count function
                if (it.rightNode.isNotEmpty()) {
                    folderSize += it.countDirectoryWithTotalSizeSmaller100000(fileCounter)
                    // reset counter since file size is saved in folderSize (else the folder size is added twice)
                    fileCounter.counter = 0
                } else {
                    folderSize += it.size
                }
            }
            // only count folders smaller than 100000
            if (folderSize < 100000) {
                fileCounter.total += folderSize
            }
            fileCounter.counter += folderSize
        } else {
            fileCounter.counter += size
        }
        return fileCounter.counter
    }

    fun getSpaceUsed(total: Int): Int {
        var totalTemp = total
        // if isNotEmpty = directory
        if (rightNode.isNotEmpty()) {
            rightNode.forEach {
                // if sub-node is another folder then call its count function
                totalTemp += if (it.rightNode.isNotEmpty()) {
                    it.getSpaceUsed(total)
                } else {
                    it.size
                }
            }
        } else {
            totalTemp += size
        }
        return totalTemp
    }

    fun collectAllDirectoryNodesWithItsSize(fileCounter: FileCounter): FileCounter {
        fileCounter.counter = 0
        // if isNotEmpty = directory
        if (rightNode.isNotEmpty()) {
            var folderSize = 0
            rightNode.forEach {
                // if sub-node is another folder then call its count function
                if (it.rightNode.isNotEmpty()) {
                    folderSize += it.collectAllDirectoryNodesWithItsSize(fileCounter).counter
                    // reset counter since file size is saved in folderSize (else the folder size is added twice)
                    fileCounter.counter = 0
                } else {
                    folderSize += it.size
                }
            }
            this.size = folderSize
            fileCounter.nodes.add(this)
            fileCounter.counter += folderSize
        } else {
            fileCounter.counter += size
        }
        return fileCounter
    }
}
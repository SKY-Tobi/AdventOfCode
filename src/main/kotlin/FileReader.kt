import java.io.File

class FileReader {
    companion object {
        fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()
    }
}
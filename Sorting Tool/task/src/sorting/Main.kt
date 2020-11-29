package sorting

import java.io.Closeable
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val paramByCommand = Params.parse(args)

    val hasParamButNoValue: (Params) -> Boolean = {
        paramByCommand.containsKey(it) && paramByCommand[it] == null
    }
    IO(
        inputFile = paramByCommand[Params.InputFile],
        outputFile = paramByCommand[Params.OutputFile]
    ).use { io ->
        when {
            hasParamButNoValue(Params.SortingType) -> {
                io.println("No sorting type defined!")
            }
            hasParamButNoValue(Params.DataType) -> {
                io.println("No data type defined!")
            }
            else -> {
                paramByCommand.keys
                    .mapNotNull { it as? Params.Unknown }
                    .forEach {
                        io.println("\"${it.key}\" is not a valid parameter. It will be skipped.")
                    }

                val sortingTool = SortingTool.get(dataType = paramByCommand[Params.DataType], io = io)
                sortingTool.read()
                when (paramByCommand[Params.SortingType] ?: "natural") {
                    "natural" -> sortingTool.sortNatural()
                    "byCount" -> sortingTool.sortByCount()
                }
            }
        }
    }

}

class IO(inputFile: String?, outputFile: String?) : Closeable {
    private val scanner = Scanner(System.`in`)

    private val reader = inputFile?.let {
        File(it).readLines().iterator()
    }

    private val writer = outputFile?.let {
        File(it).printWriter()
    }

    fun print(message: String) {
        writer?.print(message) ?: kotlin.io.print(message)
    }

    fun println(message: String) {
        writer?.println(message) ?: kotlin.io.println(message)
    }

    fun hasNextLine(): Boolean {
        return reader?.hasNext()
            ?: scanner.hasNextLine()
    }

    fun nextLine(): String {
        return reader?.next()
            ?: scanner.nextLine()
    }

    override fun close() {
        writer?.close()
    }
}
package sorting

import java.util.*

fun main(args: Array<String>) {
    val paramByCommand = Params.parse(args)

    val sortingTool = SortingTool.get(
        dataType = paramByCommand[Params.DataType]
    )
    sortingTool.read()
    when (paramByCommand[Params.SortingType] ?: "natural") {
        "natural" -> sortingTool.sortNatural()
        "byCount" -> sortingTool.sortByCount()
    }
}

val scanner = Scanner(System.`in`)
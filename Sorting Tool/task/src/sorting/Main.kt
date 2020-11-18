package sorting

import java.util.*

fun main(args: Array<String>) {
    val paramByCommand = Params.parse(args)
    val sortingTool = SortingTool.get(paramByCommand[Params.DataType])
    sortingTool.read()
    sortingTool.printResult()
}

val scanner = Scanner(System.`in`)
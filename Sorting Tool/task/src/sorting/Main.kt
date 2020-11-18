package sorting

import java.util.*

fun main(args: Array<String>) {
    val paramByCommand = Params.parse(args)

    if (paramByCommand.containsKey(Params.SortIntegers)) {
        SortIntegersTool().sort()
        return
    }

    val sortingTool = SortingTool.get(paramByCommand[Params.DataType])
    sortingTool.read()
    sortingTool.printResult()
}

val scanner = Scanner(System.`in`)
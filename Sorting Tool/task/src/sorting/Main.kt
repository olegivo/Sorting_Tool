package sorting

import java.util.*

fun main(args: Array<String>) {
    val paramByCommand = Params.parse(args)

    val hasParamButNoValue: (Params) -> Boolean = {
        paramByCommand.containsKey(it) && paramByCommand[it] == null
    }

    when {
        hasParamButNoValue(Params.SortingType) -> {
            println("No sorting type defined!")
        }
        hasParamButNoValue(Params.DataType) -> {
            println("No data type defined!")
        }
        else -> {
            paramByCommand.keys
                .mapNotNull { it as? Params.Unknown }
                .forEach {
                    println("\"${it.key}\" is not a valid parameter. It will be skipped.")
                }

            val sortingTool = SortingTool.get(dataType = paramByCommand[Params.DataType])
            sortingTool.read()
            when (paramByCommand[Params.SortingType] ?: "natural") {
                "natural" -> sortingTool.sortNatural()
                "byCount" -> sortingTool.sortByCount()
            }
        }
    }

}

val scanner = Scanner(System.`in`)
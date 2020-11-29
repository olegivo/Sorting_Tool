package sorting

sealed class Params(val key: String, val containsValue: Boolean) {
    object DataType : Params("-dataType", true)
    object SortingType : Params("-sortingType", true)
    object InputFile : Params("-inputFile", true)
    object OutputFile : Params("-outputFile", true)
    class Unknown(key: String) : Params(key, false)

    companion object {
        private val knownCommands = listOf(
            DataType,
            SortingType,
            InputFile,
            OutputFile
        ).associateBy { it.key }

        fun parse(args: Array<String>): Map<Params, String?> {
            val map = mutableMapOf<Params, String?>()

            args.forEachIndexed { index, arg ->
                val param = knownCommands[arg]
                    ?: if (index > 0 && !knownCommands.containsKey(args[index - 1])) {
                        Unknown(arg)
                    } else {
                        null
                    }

                param?.let {
                    val value = if (param.containsValue && index + 1 < args.size) {
                        args[index + 1]
                    } else {
                        null
                    }
                    map[it] = value
                }
            }

            return map
        }
    }
}
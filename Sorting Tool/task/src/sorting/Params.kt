package sorting

enum class Params(val key: String, val containsValue: Boolean) {
    DataType("-dataType", true),
    SortIntegers("-sortIntegers", false);

    companion object {
        fun parse(args: Array<String>): Map<Params, String?> {
            val commands = values().associateBy { it.key }
            return args.mapIndexed { index, s -> index to s }
                .filter { commands.containsKey(it.second) }
                .associate {
                    val param = commands.getValue(it.second)
                    val value = if (param.containsValue) args[it.first + 1] else null
                    param to value
                }
        }
    }
}
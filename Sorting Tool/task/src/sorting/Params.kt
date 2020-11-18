package sorting

enum class Params(val key: String) {
    DataType("-dataType");

    companion object {
        fun parse(args: Array<String>): Map<Params, String> {
            val commands = values().associateBy { it.key }
            return args.mapIndexed { index, s -> index to s }
                .filter { commands.containsKey(it.second) }
                .associate { commands.getValue(it.second) to args[it.first + 1] }
        }
    }
}
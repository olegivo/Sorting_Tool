package sorting

sealed class SortingTool<T>(
    private var maxItem: T,
    private val io: IO
)
    where T : Comparable<T> {

    private val items = mutableListOf<T>()
    private val map: MutableMap<T, Int> = sortedMapOf<T, Int>()
    private var count = 0
    private var maxValue: Long = Long.MIN_VALUE
    protected abstract val subjects: String
    protected abstract val itemsSeparator: String

    fun read() {
        while (io.hasNextLine()) {
            val line = io.nextLine()
            for ((item, value) in getItems(line)) {
                count++
                items.add(item)
                if (value > maxValue) {
                    maxItem = item
                    maxValue = value
                }
                map.merge(item, 1) { prev, actual -> prev + actual }
            }
        }
    }

    protected abstract fun getItems(line: String): List<Pair<T, Long>>

    protected abstract fun getMaxItemText(maxItem: T): String

    fun sortNatural() {
        println("Total $subjects: $count.")
        io.print("Sorted data:")
        map.keys
            .flatMap { key ->
                (1..map[key]!!).map { key }
            }
            .forEach { io.print("$itemsSeparator$it") }
    }

    fun sortByCount() {
        io.print("Total $subjects: $count.")
        map.entries.asSequence()
            .sortedBy { it.value }
            .forEach { entry ->
                val currentCount = entry.value
                val percent = (currentCount.toFloat() * 100 / count.toFloat()).toInt()
                io.print("\n${entry.key}: $currentCount time(s), $percent%")
            }
    }

    private class LongSortingTool(io: IO) :
        SortingTool<Long>(Long.MIN_VALUE, io) {

        override val subjects = "numbers"

        override val itemsSeparator: String = " "

        override fun getItems(line: String): List<Pair<Long, Long>> =
            line
                .split(' ')
                .mapNotNull {
                    val longItem = it.toLongOrNull()
                    if (it.isNotBlank() && longItem == null) {
                        println("\"$it\" is not a long. It will be skipped.")
                    }
                    longItem
                }
                .map { it to it }

        override fun getMaxItemText(maxItem: Long) =
            "greatest number: $maxItem "
    }

    private class LineSortingTool(io: IO) :
        SortingTool<String>("", io) {

        override val subjects: String = "lines"

        override val itemsSeparator: String = "\n"

        override fun getItems(line: String): List<Pair<String, Long>> =
            listOf(line to line.length.toLong())

        override fun getMaxItemText(maxItem: String) =
            "longest line:\n$maxItem\n"
    }

    private class WordSortingTool(io: IO) :
        SortingTool<String>("", io) {

        override val subjects: String = "words"

        override val itemsSeparator: String = " "

        override fun getItems(line: String): List<Pair<String, Long>> =
            line.split(' ')
                .filter { it.isNotBlank() }
                .map { it to it.length.toLong() }

        override fun getMaxItemText(maxItem: String) =
            "longest word: $maxItem "
    }

    companion object {
        fun get(dataType: String?, io: IO) =
            when (dataType) {
                "long" -> LongSortingTool(io)
                "line" -> LineSortingTool(io)
                "word", null -> WordSortingTool(io)
                else -> TODO()
            }
    }
}
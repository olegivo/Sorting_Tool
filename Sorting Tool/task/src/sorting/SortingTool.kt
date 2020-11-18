package sorting

sealed class SortingTool<T>(
    private var maxItem: T
)
    where T : Comparable<T> {

    private val map = mutableMapOf<T, Int>()
    private var count = 0
    private var maxValue: Long = Long.MIN_VALUE
    protected abstract val subjects: String

    fun read() {
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            for ((item, value) in getItems(line)) {
                count++
                if (value > maxValue) {
                    maxItem = item
                    maxValue = value
                }
                map.merge(item, 1) { prev, actual -> prev + actual }
            }
        }
    }

    protected abstract fun getItems(line: String): List<Pair<T, Long>>

    fun printResult() {
        println("Total $subjects: $count.")
        val maxCount = map[maxItem]!!
        val percent = (maxCount.toFloat() * 100 / count.toFloat()).toInt()
        println("The ${getMaxItemText(maxItem)}($maxCount time(s), $percent%).")
    }

    protected abstract fun getMaxItemText(maxItem: T): String

    private class LongSortingTool : SortingTool<Long>(Long.MIN_VALUE) {
        override val subjects = "numbers"
        override fun getItems(line: String): List<Pair<Long, Long>> =
            line
                .split(' ')
                .mapNotNull { it.toLongOrNull() }
                .map { it to it }

        override fun getMaxItemText(maxItem: Long) =
            "greatest number: $maxItem "
    }

    private class LineSortingTool : SortingTool<String>("") {
        override val subjects: String = "lines"

        override fun getItems(line: String): List<Pair<String, Long>> =
            listOf(line to line.length.toLong())

        override fun getMaxItemText(maxItem: String) =
            "longest line:\n$maxItem\n"
    }

    private class WordSortingTool : SortingTool<String>("") {
        override val subjects: String = "words"

        override fun getItems(line: String): List<Pair<String, Long>> =
            line.split(' ')
                .filter { it.isNotBlank() }
                .map { it to it.length.toLong() }

        override fun getMaxItemText(maxItem: String) =
            "longest word: $maxItem "
    }

    companion object {
        fun get(dataType: String?) =
            when (dataType) {
                "long" -> LongSortingTool()
                "line" -> LineSortingTool()
                "word", null -> WordSortingTool()
                else -> TODO()
            }
    }
}
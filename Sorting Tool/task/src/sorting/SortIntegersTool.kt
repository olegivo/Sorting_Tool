package sorting

class SortIntegersTool {
    fun sort() {
        val list = mutableListOf<Int>()
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            list.addAll(
                line.split(' ')
                    .mapNotNull { it.toIntOrNull() }
            )
        }

        println("Total numbers: ${list.size}.")
        print("Sorted data:")

        mergeSortBottomUp(list).forEach { print(" $it") }
    }

    private fun mergeSortBottomUp(list: List<Int>): List<Int> {
        return merged(list.map { listOf(it) }).single()
    }

    private fun merged(lists: List<List<Int>>): List<List<Int>> {
        if (lists.size < 2) return lists
        val subResult = ArrayList<List<Int>>(lists.size / 2)
        (lists.indices step 2).forEach { i ->
            subResult += if (i < lists.size - 1) {
                merge(lists[i], lists[i + 1])
            } else {
                lists[i]
            }
        }
        return merged(subResult)
    }

    private fun merge(list1: List<Int>, list2: List<Int>): List<Int> {
        var current1 = 0
        var current2 = 0
        val result = ArrayList<Int>(list1.size + list2.size)

        while (current1 < list1.size && current2 < list2.size) {
            val v1 = list1[current1]
            val v2 = list2[current2]
            if (v1 < v2) {
                result += v1
                current1++
            } else {
                result += v2
                current2++
            }
        }

        while (current1 < list1.size) {
            result += list1[current1]
            current1++
        }

        while (current2 < list2.size) {
            result += list2[current2]
            current2++
        }

        return result
    }
}
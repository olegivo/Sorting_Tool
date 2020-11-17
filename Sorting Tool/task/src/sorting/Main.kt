package sorting

import java.util.*

fun main() {
    val map = mutableMapOf<Int, Int>()
    var max = Int.MIN_VALUE
    var count = 0

    while (scanner.hasNext()) {
        val mapNotNull = scanner.next()
            .split(' ')
            .mapNotNull { it.toIntOrNull() }
        for (i in mapNotNull) {
            count++
            if (i > max) max = i
            map.merge(i, 1) { prev, actual -> prev + actual }
        }
    }

    println("""Total numbers: $count.
The greatest number: $max (${map[max]} time(s)).""")
}

val scanner = Scanner(System.`in`)
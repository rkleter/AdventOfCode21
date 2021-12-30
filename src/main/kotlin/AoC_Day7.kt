import java.io.File


fun main() {
    val inputLines = File("input7.txt").readLines()
    val pos = inputLines.first().split(',').map { s -> s.toInt() }.sortedBy { n -> n }

    val m = HashMap<Int, Int>()
    for (i in pos.first() .. pos.last()) {
        m[i] = pos.map { p -> Math.abs(p-i) }.sum()
    }
    val min1 = m.minByOrNull { me -> me.value }
    println(min1)

    val m2 = HashMap<Int, Int>()
    for (i in pos.first() .. pos.last()) {
        m2[i] = pos.map { p -> cost(Math.abs(p-i)) }.sum()
    }
    val min2 = m2.minByOrNull { me -> me.value }
    println(min2)
}

fun cost(n:Int):Int {
    var s = 0
    for (i in 1..n) s+= i
    return s
}

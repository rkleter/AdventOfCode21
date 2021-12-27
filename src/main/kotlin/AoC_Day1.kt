import java.io.File

fun main() {
    day1_a()
    day1_b()
}

fun day1_b() {
    val lines = File("input1.txt").readLines().map { line -> line.toInt()}
    var idx = 0
    var increased = 0
    while (idx < lines.size - 3) {
        if ( lines.slice(IntRange(idx,idx+2)).sum() < lines.slice(IntRange(idx+1, idx+3)).sum()) {
            increased++
        }
        idx++
    }
    println("Day1_b: Depth increased ${increased} times")
}

fun day1_a() {
    var lastDepth = -1
    var increased = 0
    val lines = File("input1.txt").readLines().map { line -> line.toInt()}
    lines.forEach{ newDepth ->
        if (lastDepth > -1 && newDepth > lastDepth) {
            increased++
        }
        lastDepth = newDepth
    }
    println("Day1_a: Depth increased ${increased} times")
}
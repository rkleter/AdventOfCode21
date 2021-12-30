fun main() {
    day2_a()
    day2_b()
}

fun day2_b() {
    val lines = readInput("Day02")
    var h = 0
    var m = 0
    var d = 0
    lines.forEach { line ->
        val a = line.split(' ')
        val i = a[1].toInt()
        when (a[0]) {
            "forward" -> { h += i; d += m * i }
            "up" -> m -= i
            "down" -> m += i
        }
    }
    println("Day2_b: ${h}, ${d}, h*d = ${h * d}")
}

private fun day2_a() {
    val lines = readInput("Day02")
    var h = 0
    var d = 0
    lines.forEach { line ->
        val a = line.split(' ')
        val i = a[1].toInt()
        when (a[0]) {
            "forward" -> h += i
            "up" -> d -= i
            "down" -> d += i
        }
    }
    println("Day2_a: ${h}, ${d}, h*d = ${h * d}")
}
import java.io.File

data class Tracker(val bitIdx: Int, var ones: Int, var zeros: Int)

fun main() {
    day3_a()
    day3_b()
}

fun day3_b() {
    val lines = File("input3.txt").readLines()
    var ox = rating(lines, {t -> t.ones > t.zeros || t.ones == t.zeros}, '1', '0')
    var co2 = rating(lines, {t -> t.zeros < t.ones || t.ones == t.zeros}, '0', '1')
    val r = Integer.parseInt(ox.first(),2) * Integer.parseInt(co2.first(),2);
    println("Day3_b: ${ox.first()}, ${co2.first()}, ${r}")
}

private fun rating(lines: List<String>, f:(t:Tracker)->Boolean, c1: Char, c2: Char): List<String> {
    var r = lines
    for (i in 0 until lines.first().length) {
        val data = trackers(r)
        if (r.size > 1) {
            val t = data[i]
            if (f(t))
                r = r.filter { s -> s[t.bitIdx] == c1 }
            else
                r = r.filter { s -> s[t.bitIdx] == c2 }
        }
    }
    return r
}

private fun day3_a() {
    val lines = File("input3.txt").readLines()
    val data = trackers(lines)
    data.reverse()
    var gamma = 0
    var epsilon = 0
    data.toList().forEachIndexed { i, t ->
        gamma += (if (t.ones > t.zeros) 1 else 0).shl(i)
        epsilon += (if (t.ones > t.zeros) 0 else 1).shl(i)
    }
    println("Day3_a: ${gamma}, ${epsilon}, ${gamma * epsilon}")
}

private fun trackers(lines: List<String>): Array<Tracker> {
    val data = Array<Tracker>(lines.first().length, { i -> Tracker(i, 0, 0) })
    lines.forEach { line ->
        line.forEachIndexed { i, c ->
            when (c) {
                '1' -> data[i].ones += 1
                '0' -> data[i].zeros += 1
            }
        }
    }
    return data
}

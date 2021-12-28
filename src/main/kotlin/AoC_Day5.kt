import java.io.File
import java.lang.Math.*

data class Point(val x:Int, val y:Int)
data class Line(val from:Point, val to:Point)

fun main() {
    val inputLines = File("input5.txt").readLines()
    val lines = parseLines(inputLines)
    val flatLinePoints = lines.map { line -> flatLinePoints(line)}.flatten()
    val gb = flatLinePoints.groupBy { p -> p }
    val c = gb.filter { it.value.size >= 2 }.size
    println("Day 5: ${c}")

    val angleLinePoints = lines.map { line -> angledLinePoints(line)}.flatten()
    val allPoints = ArrayList<Point>(flatLinePoints)
    allPoints.addAll(angleLinePoints)
    val gb2 = allPoints.groupBy { p -> p }
    val c2 = gb2.filter { it.value.size >= 2 }.size
    println("Day 5: ${c2}")
}

fun flatLinePoints(line:Line): List<Point> {
    val p = ArrayList<Point>()
    val rise = line.to.y - line.from.y
    val run = line.to.x - line.from.x
    if (run == 0) {
        for (y in min(line.from.y, line.to.y)..max(line.from.y,line.to.y))
            p.add(Point(line.from.x,y))
    }
    if (rise == 0) {
        for (x in min(line.from.x, line.to.x)..max(line.from.x,line.to.x))
            p.add(Point(x,line.from.y))
    }
    return p
}

fun angledLinePoints(line:Line): List<Point> {
    val p = ArrayList<Point>()
    val rise = line.to.y - line.from.y
    val run = line.to.x - line.from.x
    if (run == 0) return p
    if (abs(rise)/abs(run) == 1) {
        for (i in 0..abs(run)) {
            val x = if (run > 0) line.from.x + i else line.from.x - i
            val y = if (rise > 0) line.from.y + i else line.from.y - i
            p.add(Point(x,y))
        }
    }
    return p
}

fun parseLines(inputLines: List<String>): List<Line> {
    val lines = ArrayList<Line>()
    for (il in inputLines) {
        val ints = ArrayList<Int>()
        il.split(" -> ").map { s ->
            s.split(',').map { it.toInt()}.forEach { ints.add(it)}
        }
        val p1 = Point(ints[0],ints[1])
        val p2 = Point(ints[2],ints[3])
        lines.add(Line(p1,p2))
    }
    return lines
}

fun main() {
    fun cost(n:Int):Int {
        var s = 0
        for (i in 1..n) s+= i
        return s
    }

    fun min(pos:List<Int>, f:(Int,Int)->Int): Int {
        val m = HashMap<Int, Int>()
        for (i in pos.first() .. pos.last()) {
            m[i] = pos.map { p -> f(p,i) }.sum()
        }
        val min = m.minByOrNull { me -> me.value }
        return min!!.value
    }

    fun part1(pos:List<Int>): Int {
        return min(pos, { p,i -> Math.abs(p-i)})
    }

    fun part2(pos:List<Int>): Int {
        return min(pos, { p,i -> cost(Math.abs(p-i))})
    }

    val posTest = readInput("Day07_test").first().split(',').map { s -> s.toInt() }.sortedBy { n -> n }
    check(part1(posTest) == 37)
    check(part2(posTest) == 168)

    val pos = readInput("Day07").first().split(',').map { s -> s.toInt() }.sortedBy { n -> n }
    println(part1(pos))
    println(part2(pos))
}

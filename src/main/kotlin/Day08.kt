
fun main() {
    fun part1(pos:List<String>): Int {
        return 1
    }

    fun part2(pos:List<String>): Int {
        return 1
    }

    val posTest = readInput("Day08_test")
    check(part1(posTest) == 1)
    check(part2(posTest) == 1)

    val pos = readInput("Day08")
    println(part1(pos))
    println(part2(pos))
}

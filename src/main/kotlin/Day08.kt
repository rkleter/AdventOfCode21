import java.lang.Math.pow

data class Observation(val wires: List<String>, val segments: List<String>)

fun main() {
    fun observations(input: List<String>): List<Observation> {
        val obs = ArrayList<Observation>()
        for (l in input) {
            val a = l.split(" | ")
            obs.add(Observation(a.first().split(' '), a.last().split(' ')))
        }
        return obs
    }

    fun part1(input: List<String>): Int {
        val obs = observations(input)
        val uniqueDigitsLengths = listOf(2, 4, 3, 7)
        val c =
            obs.map { o -> o.segments.filter { s -> uniqueDigitsLengths.contains(s.length) } }.flatten().count { true }
        return c
    }

    fun part2(input: List<String>): Int {
        val obs = observations(input)
        val r = obs.map { o -> decode(signalToDigitMap(o.wires), o.segments) }
        return r.sum()
    }

    val posTest = readInput("Day08_test")
    check(part1(posTest) == 26)
    check(part2(posTest) == 61229)

    val pos = readInput("Day08")
    println(part1(pos))
    println(part2(pos))
}

fun decode(codeToDigitMap: MutableMap<String?, Int>, codes: List<String>): Int {
    val t = codes.reversed().map { c -> findDigit(codeToDigitMap, c) }
        .mapIndexed { index, i -> i!!.times(pow(10.0, index.toDouble())) }.sum().toInt()
    return t
}

fun findDigit(codeToDigitMap: MutableMap<String?, Int>, code: String): Int? {
    val codeKey = codeToDigitMap.keys.find { k -> code.all { k!!.contains(it) && k.length == code.length } }
    return codeToDigitMap[codeKey]
}

fun signalToDigitMap(codes: List<String>): MutableMap<String?, Int> {
    // unique by length
    val digitMap = mutableMapOf(
        codes.find { c -> c.length == 2 } to 1,
        codes.find { c -> c.length == 4 } to 4,
        codes.find { c -> c.length == 3 } to 7,
        codes.find { c -> c.length == 7 } to 8,
    )
    // len == 6 and contains all segments of 1s
    digitMap[codes.find { c -> c.length == 6 && findCodeByDigit(digitMap, 4).toCharArray().all { c.contains(it) } }] = 9
    // len == 6 and contains 1 segment of 1s
    digitMap[codes.find { c -> c.length == 6 && findCodeByDigit(digitMap, 1).toCharArray().count { c.contains(it) } == 1 }] = 6
    // remaining len == 6 code
    digitMap[codes.find { c -> c.length == 6 && !digitMap.containsKey(c) }] = 0
    // len == 5 and contains all segments of 1s
    digitMap[codes.find { c -> c.length == 5 && findCodeByDigit(digitMap, 1).toCharArray().all { c.contains(it) } }] = 3
    // len == 6 and contains only 1 segment of 1s that is also present in 6
    digitMap[codes.find { c -> c.length == 5 && findCodeByDigit(digitMap, 1).toList().intersect(findCodeByDigit(digitMap, 6).toList())
            .intersect(c.toList()).size == 1 && !digitMap.containsKey(c) }] = 5
    // remaining len == 5
    digitMap[codes.find { c -> c.length == 5 && !digitMap.containsKey(c) }] = 2

    return digitMap
}

fun findCodeByDigit(digitMap: Map<String?, Int>, i: Int): String {
    for (e in digitMap.entries) {
        if (e.value == i) return e.key!!
    }
    return ""
}


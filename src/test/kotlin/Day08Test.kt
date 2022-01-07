import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day08Test {

    @Test
    fun testCodeToDigitMap() {
        val signals = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val digits = "cdfeb fcadb cdfeb cdbaf".split(" ")
        val result = 5353

        val expected = result
        assertEquals(expected, decode(signalToDigitMap(signals),digits))
    }

    @Test
    fun testCodeToDigitMap2() {
        val signals = "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga".split(" ")
        val digits = "gecf egdcabf bgf bfgea".split(" ")
        val result = 4873

        val expected = result
        assertEquals(expected, decode(signalToDigitMap(signals),digits))
    }
}
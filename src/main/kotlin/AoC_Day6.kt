import java.io.File

fun main() {
    val population = Array<Long>(9, {  i -> 0 })
    val inputLines = File("input6.txt").readLines()
    inputLines.first().split(',').map{ s -> s.toInt() }.forEach { d ->  population[d]++}
    val days = 256
    for (step in 1..days) {
        // number of fish at day 0
        val day0 = population[0]
        for (d in 1 until population.size) {
            // copy day 8->7, day 7->6, etc
            population[d-1] = population[d]
        }
        // day 0 fish move to 6 day fish
        population[6] = day0 + population[6]
        // spawn new fish
        population[population.size - 1] = day0
        val pop_size = population.sum()
        println("Day ${step}: fish ${pop_size}")
    }
}
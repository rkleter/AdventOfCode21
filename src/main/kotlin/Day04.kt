
data class BoardCell(val r: Int, val c: Int, val num: Int, var marked: Boolean = false)

data class Board(val bn: Int, val dim: Int = 5) {
    var won: Boolean = false
    val cells = ArrayList<BoardCell>(dim * dim)
}

data class WinningSolution(val b: Board, val n: Int)

fun main() {
    val lines = readInput("Day04")
    val moves = parseMoves(lines)
    val boards = parseBoards(lines)
    val ws1 = play(moves, boards)
    val answer1 = ws1!!.b.cells.filter { c -> !c.marked }.map { c -> c.num }.sum() * ws1.n
    val ws2 = play(moves, boards, false)
    val answer2 = ws2!!.b.cells.filter { c -> !c.marked }.map { c -> c.num }.sum() * ws2.n
    println("Day 4: ${answer1}, ${answer2}")
}

fun play(moves: List<Int>, boards: List<Board>, winFirst: Boolean = true): WinningSolution? {
    fun checkWin(b: Board, c: BoardCell, m: Int): Boolean {
        if (b.won) return false
        if (c.marked) return false

        if (c.num == m) c.marked = true

        // check row
        var rowWin = true
        for (i in 0 until 5) {
            if (!b.cells.get(c.r * 5 + i).marked) rowWin = false
        }
        // check col
        var colWin = true
        for (i in 0 until 5) {
            if (!b.cells.get(c.c + 5 * i).marked) colWin = false
        }
        if (rowWin || colWin) b.won = true

        return b.won
    }

    var ws: WinningSolution? = null
    t@ for (m in moves) {
        for (b in boards) {
            for (c in b.cells) {
                if (checkWin(b, c, m) == true) {
                    println("Found winning board on move ${m} for board ${b.bn+1}")
                    printBoard(b)
                    ws = WinningSolution(b, m)
                    if (winFirst) {
                        break@t
                    }
                }
            }
        }
    }
    return ws
}

fun printBoard(b: Board) {
    // Everything after this is in red
    val red = "\u001b[31m"
    // Resets previous color codes
    val reset = "\u001b[0m"

    for (r in 0..4) {
        for (c in 0..4) {
            val cell = b.cells.get(r * 5 + c)
            if (cell.marked)
                print(red + cell.num.toString().padStart(2, ' ') + reset + ' ')
            else
                print("" + cell.num.toString().padStart(2, ' ') + ' ')
        }
        println("")
    }
}

fun parseBoards(lines: List<String>): List<Board> {
    val boards = ArrayList<Board>()
    fun makeBoard(bn: Int, lines: List<String>): Board {
        val b = Board(bn)
        for (r in 1..5) {
            val l = lines[r]
            val row = l.trim().split(Regex("\\s+")).map { s -> s.toInt() }
            row.forEachIndexed { c, n -> b.cells.add(BoardCell(r - 1, c, n)) }
        }
        return b
    }

    var lines = lines.subList(1, lines.size)
    var bn = 0
    while (lines.size > 0) {
        val board = makeBoard(bn, lines)
        boards.add(board)
        lines = lines.subList(6, lines.size)
        bn++
    }
    return boards
}

fun parseMoves(lines: List<String>): List<Int> {
    return lines.first().split(',').map { s -> s.toInt() }
}

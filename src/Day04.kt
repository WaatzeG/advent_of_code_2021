fun main() {

    data class Board(val numbers: List<List<Int>>) {
        private val rows = numbers.map { it.toMutableList() }.toMutableList()
        private val columns = (0 until 5).map { columnIndex -> rows.map { row -> row[columnIndex] }.toMutableList() }.toMutableList()

        fun sum() = rows.sumOf { it.sum() }

        fun mark(number: Int) {
            rows.forEach { it.remove(number) }
            columns.forEach { it.remove(number) }
        }

        fun won(): Boolean =
            rows.minOf { it.size } == 0 || columns.minOf { it.size } == 0
    }


    fun parseDrawings(input: List<String>) = input[0].split(",").map(String::toInt)

    fun parseBoards(input: List<String>): List<Board> {
        return input.subList(2, input.size)
            .map { it.split(" ").filter(String::isNotBlank).map(String::toInt) }.filter { it.isNotEmpty() }
            .let { rows -> (rows.indices step 5).map { rows.subList(it, it + 5) }.map(::Board) }
    }

    fun part1(input: List<String>) {
        val drawings = parseDrawings(input)
        val boards = parseBoards(input)

        val (lastDraw, board) = drawings.firstNotNullOf { draw ->
            boards.firstOrNull {
                it.mark(draw)
                it.won()
            }?.let {
                draw to it
            }
        }
        println("Winning board = $board, Draw = $lastDraw, Value=${board.sum() * lastDraw}")
    }

    fun part2(input: List<String>) {
        val drawings = parseDrawings(input)
        val boards = parseBoards(input).toMutableList()

        val (lastDraw, board) = drawings.firstNotNullOf { draw ->
            boards.filter {
                it.mark(draw)
                it.won()
            }.let {
                boards.removeAll(it)
                if (boards.size == 0) {
                    draw to it.single()
                } else {
                    null
                }
            }
        }
        println("Last to win board = $board, Draw = $lastDraw, Value=${board.sum() * lastDraw}")
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day04")
    part1(input)
    part2(input)
}

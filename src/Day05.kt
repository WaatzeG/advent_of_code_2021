import kotlin.math.absoluteValue

fun main() {

    data class Coord(val x: Int, val y: Int)

    fun getCoords(input: List<String>) = input.map { line ->
        line.split(" -> ")
            .map { coord ->
                coord.split(",").let { Coord(it[0].toInt(), it[1].toInt()) }
            }
    }.map {
        it[0] to it[1]
    }

    fun getLines(points: List<Pair<Coord, Coord>>) = points.flatMap { (start, end) ->
        if (start.x == end.x) {
            (minOf(start.y, end.y)..maxOf(start.y, end.y)).map { y -> Coord(start.x, y) }
        } else if (start.y == end.y) {
            (minOf(start.x, end.x)..maxOf(start.x, end.x)).map { x -> Coord(x, start.y) }
        } else {
            val deltaX = end.x - start.x
            val deltaY = end.y - start.y
            (0..deltaX.absoluteValue).map {
                val x = if (deltaX > 0) {
                    start.x + it
                } else {
                    start.x - it
                }
                val y = if (deltaY > 0) {
                    start.y + it
                } else {
                    start.y - it
                }
                Coord(x, y)
            }
        }
    }

    fun part1(input: List<String>) {
        val straightLineCoords = getCoords(input)
            .filter { it.first.x == it.second.x || it.first.y == it.second.y }

        val overlap = getLines(straightLineCoords).groupBy { it.x to it.y }
            .filter { it.value.size >= 2 }
            .size
        println("Part1 :Overlap of at least two lines = $overlap")
    }

    fun part2(input: List<String>) {
        val points = getCoords(input)

        val overlap = getLines(points).groupBy { it.x to it.y }
            .filter { it.value.size >= 2 }
            .size
        println("Part2 :Overlap of at least two lines = $overlap")
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day05")
    part1(input)
    part2(input)
}

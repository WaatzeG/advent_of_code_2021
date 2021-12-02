enum class Direction {
    forward,
    down,
    up
}

fun main() {

    data class Position(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)

    data class Movement(val direction: Direction, val amount: Int) {
        fun move(position: Position): Position {
            return when (direction) {
                Direction.up      -> position.copy(depth = position.depth - amount)
                Direction.down    -> position.copy(depth = position.depth + amount)
                Direction.forward -> position.copy(horizontal = position.horizontal + amount)
            }
        }
    }

    fun part1(input: List<String>): Position {
        val startPosition = Position()
        val movements = input.map { it.split(" ").let { Movement(Direction.valueOf(it[0]), it[1].toInt()) } }
        return movements.fold(startPosition) { position, movement -> movement.move(position) }
    }

    data class MovementDay2(val direction: Direction, val amount: Int) {
        fun move(position: Position): Position {
            return when (direction) {
                Direction.up      -> position.copy(aim = position.aim - amount)
                Direction.down    -> position.copy(aim = position.aim + amount)
                Direction.forward -> position.copy(horizontal = position.horizontal + amount, depth = position.depth + (position.aim * amount))
            }
        }
    }

    fun part2(input: List<String>): Position {
        val startPosition = Position()
        val movements = input.map { it.split(" ").let { MovementDay2(Direction.valueOf(it[0]), it[1].toInt()) } }
        return movements.fold(startPosition) { position, movement -> movement.move(position) }
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

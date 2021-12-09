import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>) {
        val horizontalPositions = input[0].split(",").map(String::toInt)
        val minimumStepsPosition = (horizontalPositions.minOf { it }..horizontalPositions.maxOf { it }).minOf { position ->
            horizontalPositions.sumOf { (it - position).absoluteValue }
        }

        println("Part 1: Position with minimum steps = $minimumStepsPosition")
    }

    fun part2(input: List<String>) {
        val horizontalPositions = input[0].split(",").map(String::toInt)
        val minimumStepsPosition = (horizontalPositions.minOf { it }..horizontalPositions.maxOf { it }).minOf { position ->
            horizontalPositions.sumOf {
                (1..(it - position).absoluteValue).let { delta ->
                    if (!delta.isEmpty()) {
                        delta.reduce { acc, i -> acc + i }
                    } else {
                        0
                    }
                }
            }
        }

        println("Part 2: Position with minimum steps = $minimumStepsPosition")
    }
    // test if implementation meets criteria from the description, like:
    val input = readInput("Day07")
    part1(input)
    part2(input)
}

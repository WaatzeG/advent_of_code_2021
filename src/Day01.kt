fun main() {
    fun getIncreaseCount(numbers: List<Int>): Int {
        return numbers.zipWithNext { prev, cur -> cur > prev }
            .count { it }
    }

    fun part1(input: List<Int>): Int {
        return getIncreaseCount(input)
    }

    fun part2(input: List<Int>): Int {
        val groups = input.windowed(3) { it.sum() }
        return getIncreaseCount(groups)
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01").map(String::toInt)
    println(part1(input))
    println(part2(input))
}

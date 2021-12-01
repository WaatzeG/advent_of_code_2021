fun main() {
    fun getIncreaseCount(numbers: List<Int>): Int {
        var previousValue: Int? = null
        return numbers.count { currentValue ->
            val valueIncreased = previousValue.let { it != null && it < currentValue }
            previousValue = currentValue
            valueIncreased
        }
    }

    fun part1(input: List<Int>): Int {
        return getIncreaseCount(input)
    }

    fun part2(input: List<Int>): Int {
        val groups = input.mapIndexedNotNull() { index, _ ->
            if (index + 2 > input.size - 1) {
                null
            } else {
                input.slice(index..index + 2).sum()
            }
        }
        return getIncreaseCount(groups)
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01").map(String::toInt)
    println(part1(input))
    println(part2(input))
}

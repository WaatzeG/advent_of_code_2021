fun main() {

    fun addDays(fish: Map<Int, Long>, days: Int) = (1..days).fold(fish) { fishToday, _ ->
        fishToday
            .toSortedMap(reverseOrder())
            .flatMap { (generation, number) ->
                when (generation) {
                    0    -> listOf(
                        6 to (fishToday[7] ?: 0) + number,
                        8 to number
                    )
                    else ->
                        listOf(generation - 1 to number)
                }
            }.toMap()
    }

    fun getFishByGeneration(initialFish: List<Int>) = initialFish.groupBy { it }
        .let { groups ->
            groups.keys.associateWith { groups.getValue(it).count().toLong() }
        }

    fun part1(input: List<String>) {
        val initialFish = input.first().split(",").map(String::toInt)
        val fishByGeneration = getFishByGeneration(initialFish)
        val fishAfter80Days = addDays(fishByGeneration, 80).values.sum()
        println("Part 1: number of fish after 80 generations = $fishAfter80Days")
    }

    fun part2(input: List<String>) {
        val initialFish = input.first().split(",").map(String::toInt)
        val fishByGeneration = getFishByGeneration(initialFish)
        val fishAfter256Days = addDays(fishByGeneration, 256).values.sum()
        println("Part 2: number of fish after 256 generations = $fishAfter256Days")
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day06")
    part1(input)
    part2(input)
}

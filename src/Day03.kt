fun main() {

    fun most(list: List<Int>): Int {
        return if (list.count { it == 1 } >= list.count { it == 0 }) {
            1
        } else {
            0
        }
    }

    fun least(list: List<Int>): Int {
        return if (list.count { it == 1 } < list.count { it == 0 }) {
            1
        } else {
            0
        }
    }

    fun toDecimal(bits: List<Int>): Int = bits.reduce { acc: Int, i: Int -> (acc shl (1)) + i }

    fun part1(input: List<String>) {
        val columns = (1..12).map { index ->
            input.map { it[index - 1].digitToInt() }
        }
        val gamma = columns.map(::most).let(::toDecimal)
        val epsilon = columns.map(::least).let(::toDecimal)
        val powerConsumption = gamma * epsilon

        println("Gamma : $gamma, Epsilon : $epsilon, Power Consumption: $powerConsumption")
    }

    fun part2(input: List<String>) {
        val lines = input.map { line -> line.map(Char::digitToInt) }

        val oxygen = (0 until 12).fold(lines) { codes, index ->
            if (codes.size == 1) {
                codes
            } else {
                val currentColumn = codes.map { it[index] }
                codes.filter { it[index] == most(currentColumn) }
            }
        }.single().let(::toDecimal)

        val co2Scrubber = (0 until 12).fold(lines) { codes, index ->
            if (codes.size == 1) {
                codes
            } else {
                val currentColumn = codes.map { it[index] }
                codes.filter { it[index] == least(currentColumn) }
            }
        }.single().let(::toDecimal)

        val lifeSupportRating = oxygen * co2Scrubber

        println("Oxygen: $oxygen, CO2Scrubber: $co2Scrubber, Life Support Rating: $lifeSupportRating")
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day03")
    part1(input)
    part2(input)
}

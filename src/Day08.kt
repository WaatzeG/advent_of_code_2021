import java.util.stream.Collectors.toSet
import kotlin.math.pow

fun main() {


    fun part1(input: List<String>) {
        val occurrence = input.sumOf { line ->
            line.split("|")[1].split(" ")
                .count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
        }
        println("Part 1: $occurrence")
    }

    fun part2(input: List<String>) {
        val sum = input.sumOf { line ->
            line.split("|").let {
                val wiring = it[0]
                val numbers = it[1]
                val digits = wiring.split(" ")
                    .filter { it.isNotBlank() }
                    .groupBy({ it.length }) { it.toSet() }
                    .let { length ->
                        mapOf(
                            length.getValue(7).single() to 8,
                            length.getValue(4).single() to 4,
                            length.getValue(3).single() to 7,
                            length.getValue(2).single() to 1,
                            length.getValue(6).single { it + length.getValue(4).single() == it } to 9, //9 + 4 stays 9
                            length.getValue(6).single { it + length.getValue(2).single() != it } to 6, //6 + 1 == 6
                            length.getValue(6).single {
                                it + length.getValue(2).single() == it
                                        && it + length.getValue(4).single() != it
                            } to 0, //0 + 1 == 0 && 0 + 4 != 4 (eliminates 9)
                            length.getValue(5).single { it + length.getValue(2).single() == it } to 3, //3 + 1 == 3
                            length.getValue(5).single {
                                it + length.getValue(6)
                                    .single { nine -> nine + length.getValue(4).single() == nine } == length.getValue(7).single()
                            } to 2, //2+9==8
                            length.getValue(5).single {
                                it + length.getValue(6).single { it + length.getValue(2).single() != it } == length.getValue(6)
                                    .single { it + length.getValue(2).single() != it }
                            } to 5 //5+6==5
                        )
                    }

                //add them up
                numbers.split(" ").filter { it.isNotBlank() }
                    .reversed()
                    .foldIndexed(0L) { index, sum, word -> sum + (digits.getValue(word.toSet()) * 10.0.pow(index)).toLong() }
            }

        }
        println("Part 2: $sum")
    }

    val input = readInput("Day08")
    part1(input)
    part2(input)
}

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
                    .groupBy { it.length }
                    .let { length ->
                        mapOf(
                            8 to length.getValue(7).single(),
                            4 to length.getValue(4).single(),
                            7 to length.getValue(3).single(),
                            1 to length.getValue(2).single(),
                            9 to length.getValue(6).single { it.toSet() + length.getValue(4).single().toSet() == it.toSet() }, //9 + 4 stays 9
                            0 to length.getValue(6).single {
                                it.toSet() + length.getValue(2).single().toSet() == it.toSet() && it.toSet() + length.getValue(4)
                                    .single().toSet() != it.toSet()
                            }, //0 + 1 == 0 && 0 + 4 != 4 (eliminates 6)
                            6 to length.getValue(6).single { it.toSet() + length.getValue(2).single().toSet() != it.toSet() }, //6 + 1 == 6
                            3 to length.getValue(5).single { it.toSet() + length.getValue(2).single().toSet() == it.toSet() }, //3 + 1 == 3
                            2 to length.getValue(5).single {
                                it.toSet() + length.getValue(6)
                                    .single { nine -> nine.toSet() + length.getValue(4).single().toSet() == nine.toSet() }
                                    .toSet() == length.getValue(7).single().toSet()
                            }, //2+9==8
                            5 to length.getValue(5).single {
                                it.toSet() + length.getValue(6).single { it.toSet() + length.getValue(2).single().toSet() != it.toSet() }
                                    .toSet() == length.getValue(6).single { it.toSet() + length.getValue(2).single().toSet() != it.toSet() }
                                    .toSet()
                            } //5+6==5
                        )
                    }

                //add them up
                numbers.split(" ").filter { it.isNotBlank() }
                    .foldIndexed(0L) { index, acc, word ->
                        acc + (digits.entries.single { it.value.toSet() == word.toSet() }.key * 10.0.pow((3.0 - index))
                                ).toLong()
                    }
            }

        }
        println("Part 2: $sum")
    }

    val input = readInput("Day08")
    part1(input)
    part2(input)
}

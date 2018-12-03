import java.io.File

fun main(args : Array<String>) {
    val resource = Int::class.java.getResource("day1.input.txt")
    val input = File(resource.toURI()).readLines()
    println("Part 1: " + Day1.part1(input))
    println("Part 2: " + Day1.part2(input))
}

object Day1 {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.sum()
    }

    fun part2(inputs: List<String>): Int {
        val frequencies = mutableSetOf(0)
        val intInputs = inputs.map { it.toInt() }
        var sum = 0
        while (true) {
            for(input in intInputs) {
                sum += input

                if (!frequencies.add(sum)) return sum

                frequencies.add(sum)
            }
        }
    }
}

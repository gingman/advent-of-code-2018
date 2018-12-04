import java.io.File

fun main(args : Array<String>) {
    val resource = Int::class.java.getResource("day3.input.txt")
    val input = File(resource.toURI()).readLines()
    println("Part 1: " + Day3.part1(input))
    println("Part 2: " + Day3.part2(input))
}

object Day3 {
    fun part1(inputs: List<String>): Int {
        val regexp =  """#(\d+)\s@\s(\d+),(\d+):\s(\d+)x(\d+)""".toRegex()
        val coordinates = hashMapOf<String, Int>()
        inputs.forEach { input ->
            val matchResult = regexp.find(input)
            val (id, left, top, width, height) = matchResult!!.destructured
            for(x in left.toInt() until left.toInt() + width.toInt()) {
                for (y in top.toInt() until top.toInt() + height.toInt()) {
                    val position = "$x,$y"
                    if (coordinates[position] != null) {
                        coordinates[position] = coordinates[position]!!.plus(1)
                    } else {
                        coordinates[position] = 1
                    }
                }
            }
        }
        return coordinates.values.filter { it > 1 }.count()
    }

    fun part2(inputs: List<String>): String {
        val regexp =  """#(\d+)\s@\s(\d+),(\d+):\s(\d+)x(\d+)""".toRegex()
        val coordinates = hashMapOf<String, Int>()

        inputs.forEach { input ->
            val matchResult = regexp.find(input)
            val (id, left, top, width, height) = matchResult!!.destructured
            for(x in left.toInt() until left.toInt() + width.toInt()) {
                for (y in top.toInt() until top.toInt() + height.toInt()) {
                    val position = "$x,$y"
                    if (coordinates[position] != null) {
                        coordinates[position] = coordinates[position]!!.plus(1)
                    } else {
                        coordinates[position] = 1
                    }
                }
            }
        }

        // We have all our occurrences filled in
        inputs.forEach { input ->
            val matchResult = regexp.find(input)
            val (id, left, top, width, height) = matchResult!!.destructured
            var allMatchesOnce = true
            for(x in left.toInt() until left.toInt() + width.toInt()) {
                for (y in top.toInt() until top.toInt() + height.toInt()) {
                    val position = "$x,$y"
                    if (coordinates[position]!! != 1) allMatchesOnce = false
                }
            }

            if (allMatchesOnce) return id
        }

        return ""
    }
}

import java.io.File

fun main(args : Array<String>) {
    val resource = Int::class.java.getResource("day5.input.txt")
    val input = File(resource.toURI()).readText().trim()
    println("Part 1: " + Day5.part1(input))
    println("Part 2: " + Day5.part2(input))
}


object Day5 {
    fun part1(input: String): Int {
        val regexp = getAdjacentUnitRegex()
        val units = removeAdjacentUnit(input, regexp)
        return units.count()
    }

    fun part2(input: String): Int {
        val regexp = getAdjacentUnitRegex()
        val unitsLength = mutableListOf<Int>()

        ('a'..'z').forEach {
            val inputWithoutChar = Regex("$it", RegexOption.IGNORE_CASE).replace(input, "")
            unitsLength.add(removeAdjacentUnit(inputWithoutChar, regexp).count())
        }

        return unitsLength.min()!!
    }

    private fun removeAdjacentUnit(input: String, regexp: Regex): String {
        if (regexp.containsMatchIn(input)) {
            return removeAdjacentUnit(regexp.replaceFirst(input, ""), regexp)
        }
        return input
    }

    private fun getAdjacentUnitRegex(): Regex {
        val combinations = mutableListOf<String>()
        ('a'..'z').forEach {
            val downcase = it
            val upcase = it.toUpperCase()
            combinations.add("$downcase$upcase")
            combinations.add("$upcase$downcase")
        }

        return combinations.joinToString("|").toRegex()
    }
}

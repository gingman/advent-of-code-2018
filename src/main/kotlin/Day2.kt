import java.io.File

fun main(args : Array<String>) {
    val resource = Int::class.java.getResource("day2.input.txt")
    val input = File(resource.toURI()).readLines()
    println("Part 1: " + Day2.part1(input))
    println("Part 2: " + Day2.part2(input))
}

class Barcode(barcode: String) {
    private val occurrences = hashMapOf<Char, Int>()
    private var characters: List<Char>

    init {
        barcode.groupingBy { it }.eachCountTo(occurrences)
        characters = barcode.toCharArray().toList()
    }

    fun findMatchingBarcode(barcodes: List<Barcode>): Barcode? {
        return barcodes.firstOrNull { matchesByOneCharacter(it) }
    }

    fun findCommonCharacters(otherBarcode: Barcode): String {
        var commonCharacters: List<Char> = mutableListOf()

        characters.forEachIndexed { index, character ->
            if (otherBarcode.characters[index] == character) {
                commonCharacters += character
            }
        }

        return commonCharacters.joinToString("")
    }

    fun hasCharacterWithExactOccurrenceOf(numberOfOccurrence: Int): Boolean {
        return occurrences.containsValue(numberOfOccurrence)
    }

    private fun matchesByOneCharacter(otherBarcode: Barcode): Boolean {
        var differences = 0

        characters.forEachIndexed { index, character ->
            if (otherBarcode.characters[index] != character) {
                differences++
            }
        }

        return differences == 1
    }
}

object Day2 {
    fun part1(input: List<String>): Int {
        val barcodes = input.map { Barcode(it) }
        return barcodes.filter { it.hasCharacterWithExactOccurrenceOf(2) }.count() * barcodes.filter { it.hasCharacterWithExactOccurrenceOf(3) }.count()
    }

    fun part2(input: List<String>): String {
        val barcodes = input.map { Barcode(it) }
        for (barcode in barcodes) {
            val foundBarcode = barcode.findMatchingBarcode(barcodes)

            if (foundBarcode != null) {
                return barcode.findCommonCharacters(foundBarcode)
            }
        }

        return ""
    }
}

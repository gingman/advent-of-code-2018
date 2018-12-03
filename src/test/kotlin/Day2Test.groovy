import spock.lang.Specification

class Day2Test extends Specification {
    def "Part1"() {
        expect:
        new Day2().part1([
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab"
        ]) == 12
    }

    def "Part2"() {
        expect:
        new Day2().part2([
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz"
        ]) == "fgij"
    }
}

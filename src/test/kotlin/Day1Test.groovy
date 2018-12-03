import spock.lang.Specification

class Day1Test extends Specification {
    def "Part1"() {
        expect:
        new Day1().part1(["+1", "+1", "+1"]) == 3
        new Day1().part1(["+1", "+1", "-2"]) == 0
        new Day1().part1(["-1", "-2", "-3"]) == -6
    }

    def "Part2"() {
        expect:
        new Day1().part2(["+1", "-1"]) == 0
        new Day1().part2(["+3", "+3", "+4", "-2", "-4"]) == 10
        new Day1().part2(["-6", "+3", "+8", "+5", "-6"]) == 5
        new Day1().part2(["+7", "+7", "-2", "-7", "-4"]) == 14
    }
}

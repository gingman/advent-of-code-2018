import spock.lang.Specification

class Day5Test extends Specification {
    def "Part1"() {
        expect:
        new Day5().part1(["dabAcCaCBAcCcaDA"]) == 10
    }

    def "Part2"() {
        expect:
        new Day5().part2(["dabAcCaCBAcCcaDA"]) == 4
    }
}

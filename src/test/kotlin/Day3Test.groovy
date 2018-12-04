import spock.lang.Specification

class Day3Test extends Specification {
    def "Part1"() {
        expect:
        new Day3().part1([
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        ]) == 4
    }

    def "Part2"() {
        expect:
        new Day3().part2([
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        ]) == "3"
    }
}

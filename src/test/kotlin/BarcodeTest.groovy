import spock.lang.Specification

class BarcodeTest extends Specification {
    def "hasExactOccurrenceOf"() {
        expect:
        !new Barcode("abcdef").hasCharacterWithExactOccurrenceOf(2)
        new Barcode("bababc").hasCharacterWithExactOccurrenceOf(2)
        new Barcode("abbcde").hasCharacterWithExactOccurrenceOf(2)
        new Barcode("abcccd").hasCharacterWithExactOccurrenceOf(3)
        !new Barcode("abcd").hasCharacterWithExactOccurrenceOf(3)
    }

    def "MatchesByOneCharacter"() {
        expect:
        !new Barcode("abcdef").matchesByOneCharacter(new Barcode("abfga1"))
        new Barcode("abcdef").matchesByOneCharacter(new Barcode("abddef"))
    }
}

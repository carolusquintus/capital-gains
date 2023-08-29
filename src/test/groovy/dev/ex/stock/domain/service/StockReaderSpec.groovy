package dev.ex.stock.domain.service

import spock.lang.Specification

class StockReaderSpec extends Specification {

    def "Process input when String is single line with one array"() {
        given:
        def reader = new StockReader()

        and:
        def input = '[{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]'

        and:
        def stream = new ByteArrayInputStream(input.getBytes())

        when:
        def result = reader.process(stream)

        then:
        result
        !result.empty
        result.size() == 1
    }

    def "Process input when String is multi line with one array"() {
        given:
        def reader = new StockReader()

        and:
        def input = '''[
            {"operation":"buy", "unit-cost":10.00, "quantity": 100},
            {"operation":"sell", "unit-cost":15.00, "quantity": 50},
            {"operation":"sell", "unit-cost":15.00, "quantity": 50}
        ]'''

        and:
        def stream = new ByteArrayInputStream(input.getBytes())

        when:
        def result = reader.process(stream)

        then:
        result
        !result.empty
        result.size() == 1
    }

    def "Process input when String is multi line with two arrays"() {
        given:
        def reader = new StockReader()

        and:
        def input = '''[
            {"operation":"buy", "unit-cost":10.00, "quantity": 100},
            {"operation":"sell", "unit-cost":15.00, "quantity": 50},
            {"operation":"sell", "unit-cost":15.00, "quantity": 50}
        ]
        [
            {"operation":"buy", "unit-cost":10.00, "quantity": 10000},
            {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
            {"operation":"sell", "unit-cost":5.00, "quantity": 5000}
        ]
        
        
        '''

        and:
        def stream = new ByteArrayInputStream(input.getBytes())

        when:
        def result = reader.process(stream)

        then:
        result
        !result.empty
        result.size() == 2
    }

}
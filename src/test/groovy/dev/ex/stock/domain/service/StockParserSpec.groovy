package dev.ex.stock.domain.service

import spock.lang.Specification

class StockParserSpec extends Specification {

    def "Process List of String to single List of Stock"() {
        given:
        def parser = new StockParser()

        and:
        def input = [
            '[{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]'
        ]

        when:
        def result = parser.process(input)

        then:
        result
        !result.empty
        result.size() == 1
        result[0].size() == 3
    }

    def "Process List of String to List of List of Stock"() {
        given:
        def parser = new StockParser()

        and:
        def input = [
            '[{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]',
            '[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},{"operation":"sell", "unit-cost":20.00, "quantity": 5000},{"operation":"sell", "unit-cost":5.00, "quantity": 5000}]',
            '[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},{"operation":"buy", "unit-cost":25.00, "quantity": 5000},{"operation":"sell", "unit-cost":15.00, "quantity": 10000},{"operation":"sell", "unit-cost":25.00, "quantity": 5000}]'
        ]

        when:
        def result = parser.process(input)

        then:
        result
        !result.empty
        result.size() == 3
        result[0].size() == 3
        result[1].size() == 3
        result[2].size() == 4
    }

}
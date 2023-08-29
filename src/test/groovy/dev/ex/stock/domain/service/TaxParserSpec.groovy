package dev.ex.stock.domain.service

import dev.ex.stock.domain.entity.Tax
import spock.lang.Specification

class TaxParserSpec extends Specification {

    def "Process List of Tax to single List of parsed json String"() {
        given:
        def parser = new TaxParser()

        and:
        def input = [
            [
                new Tax(new BigDecimal("0.0")),
                new Tax(new BigDecimal("0.0")),
                new Tax(new BigDecimal("0.0")),
            ]
        ]

        when:
        def result = parser.process(input)
        def matchTax = result =~ /tax/

        then:
        result
        !result.empty
        matchTax.size() == 3
    }

}
package dev.ex.stock.domain.service

import dev.ex.stock.domain.entity.Stock
import dev.ex.stock.domain.entity.Tax
import spock.lang.Specification

import static dev.ex.stock.domain.entity.Operation.BUY
import static dev.ex.stock.domain.entity.Operation.SELL

class TaxCalculatorSpec extends Specification {

    def "Process single List of Stock, calculates tax and responds to single List of Tax"() {
        given:
        def calculator = new TaxCalculator()

        and:
        def input = [
            [
                new Stock(BUY, new BigDecimal("10.00"), 100L),
                new Stock(SELL, new BigDecimal("15.00"), 50L),
                new Stock(SELL, new BigDecimal("15.00"), 50L),
            ]
        ]

        when:
        def result = calculator.process(input)

        then:
        result
        !result.empty
        result.size() == 1
        result[0].size() == 3
    }

    def "Process List of List of Stock, calculates tax and responds List of List of Tax"() {
        given:
        def calculator = new TaxCalculator()

        and:
        def input = [
            [
                new Stock(BUY, new BigDecimal("10.00"), 100L),
                new Stock(SELL, new BigDecimal("15.00"), 50L),
                new Stock(SELL, new BigDecimal("15.00"), 50L),
            ],
            [
                new Stock(BUY, new BigDecimal("10.00"), 10_000L),
                new Stock(SELL, new BigDecimal("2.00"), 5_000L),
                new Stock(SELL, new BigDecimal("20.00"), 2_000L),
                new Stock(SELL, new BigDecimal("20.00"), 2_000L),
                new Stock(SELL, new BigDecimal("25.00"), 1_000L),
                new Stock(BUY, new BigDecimal("20.00"), 10_000L),
                new Stock(SELL, new BigDecimal("15.00"), 5_000L),
                new Stock(SELL, new BigDecimal("30.00"), 4_350L),
                new Stock(SELL, new BigDecimal("30.00"), 650L),
            ]
        ]

        when:
        def result = calculator.process(input)

        then:
        result
        !result.empty
        result.size() == 2
        result[0].size() == 3
        result[1].size() == 9
        result[1][4] == new Tax(new BigDecimal("3000.00"))
        result[1][7] == new Tax(new BigDecimal("3700.00"))
    }

}
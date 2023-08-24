package dev.ex.stock.domain.service;

import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.entity.Tax;
import dev.ex.stock.domain.service.shared.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TaxCalculator implements Service<List<List<Stock>>, List<List<Tax>>> {

    private static final BigInteger PERCENTAGE_TAX = new BigInteger("20");
    private static final BigDecimal TOTAL_AMOUNT = new BigDecimal("20000.00");

    private static final Tax ZERO_TAX = new Tax(new BigDecimal("0.00"));

    @Override
    public List<List<Tax>> process(List<List<Stock>> input) {
        return input.stream()
            .map(this::calculateTaxes)
            .toList();
    }

    private List<Tax> calculateTaxes(List<Stock> stocks) {
        var taxes = new ArrayList<Tax>();

        for (var stock: stocks) {
            switch (stock.operation()) {
                case BUY -> taxes.add(ZERO_TAX);
                case SELL -> {
                    if (isLowerEqualTotalAmount(stock)) {
                        taxes.add(ZERO_TAX);
                    }
                }
            }
        }

        return taxes;
    }

    private boolean isLowerEqualTotalAmount(Stock stock) {
        var totalAmountCalculated = stock.unitCost().multiply(new BigDecimal(stock.quantity()));
        var comparisonResult = totalAmountCalculated.compareTo(TOTAL_AMOUNT);
        return comparisonResult <= 0;
    }


}

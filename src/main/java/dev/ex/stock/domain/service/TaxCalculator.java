package dev.ex.stock.domain.service;

import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.entity.Tax;
import dev.ex.stock.domain.service.shared.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dev.ex.stock.domain.entity.Operation.BUY;

public class TaxCalculator implements Service<List<List<Stock>>, List<List<Tax>>> {

    @Override
    public List<List<Tax>> process(List<List<Stock>> input) {
        return input.stream()
            .map(this::calculateTaxes)
            .toList();
    }

    private List<Tax> calculateTaxes(List<Stock> stocks) {
        final var zeroTax = new Tax(new BigDecimal("0.00"));
        var taxes = new ArrayList<Tax>();

        for (var stock: stocks) {
            switch (stock.operation()) {
                case BUY -> taxes.add(zeroTax);
                case SELL -> {
                }
            }
        }

        return taxes;
    }
}

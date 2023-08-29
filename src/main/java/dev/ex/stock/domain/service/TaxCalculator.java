package dev.ex.stock.domain.service;

import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.entity.Tax;
import dev.ex.stock.domain.service.shared.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dev.ex.stock.domain.entity.Operation.*;
import static java.math.RoundingMode.FLOOR;

public class TaxCalculator implements Service<List<List<Stock>>, List<List<Tax>>> {

    private static final BigDecimal PERCENTAGE_TAX = new BigDecimal("0.2");
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

        var totalStockQuantity = new BigDecimal("0.0");
        var weightedAveragePrice = new BigDecimal("0.0");
        var previousBuy = new Stock(BUY, new BigDecimal("0.0"), 0L);

        for (var stock : stocks) {
            switch (stock.operation()) {
                case BUY -> {
                    taxes.add(ZERO_TAX);
                    totalStockQuantity = totalStockQuantity.add(stock.quantityDecimal());
                    weightedAveragePrice = weightedAveragePrice(previousBuy, stock);
                    previousBuy = stock;
                }
                case SELL -> {
                    if (stock.isLowerEqualTotalAmount(TOTAL_AMOUNT)) {
                        taxes.add(ZERO_TAX);
                        break;
                    }
                    totalStockQuantity = totalStockQuantity.subtract(stock.quantityDecimal());
                    var profit = calculateProfit(stock, weightedAveragePrice);
                    taxes.add(calculateTax(stock, weightedAveragePrice, profit));
                }
            }
        }

        return taxes;
    }

    private BigDecimal weightedAveragePrice(Stock currentBought, Stock newBought) {
        var currentCalculus = currentBought.quantityDecimal().multiply(currentBought.unitCost());
        var newCalculus = newBought.quantityDecimal().multiply(newBought.unitCost());
        var quantitySum = currentBought.quantityDecimal().add(newBought.quantityDecimal());

        return currentCalculus.add(newCalculus).divide(quantitySum, 2, FLOOR);
    }

    private BigDecimal calculateProfit(Stock stockSold, BigDecimal weightedAveragePrice) {
        return stockSold.totalAmount().subtract(stockSold.quantityDecimal().multiply(weightedAveragePrice));
    }

    private Tax calculateTax(Stock stock, BigDecimal weightedAveragePrice, BigDecimal profit) {
        if (stock.unitCost().compareTo(weightedAveragePrice) == -1) {
            return ZERO_TAX;
        }
        if (profit.compareTo(TOTAL_AMOUNT) == 1) {
            return new Tax(profit.multiply(PERCENTAGE_TAX).setScale(2, FLOOR));
        }
        return ZERO_TAX;
    }


}

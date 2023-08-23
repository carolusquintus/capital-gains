package dev.ex.stock.application.port.input;

import dev.ex.stock.application.usecase.CapitalGainsUseCase;
import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.entity.Tax;
import dev.ex.stock.domain.service.StockParser;
import dev.ex.stock.domain.service.StockReader;
import dev.ex.stock.domain.service.TaxCalculator;
import dev.ex.stock.domain.service.TaxParser;
import dev.ex.stock.domain.service.shared.Service;

import java.io.InputStream;
import java.util.List;

public class CapitalGainsInputPort implements CapitalGainsUseCase {

    private Service<InputStream, List<String>> stockReader;
    private Service<List<String>, List<List<Stock>>> stockParser;
    private Service<List<List<Stock>>, List<List<Tax>>> taxCalculator;
    private Service<List<List<Tax>>, String> taxParser;

    public CapitalGainsInputPort() {
        this.stockReader = new StockReader();
        this.stockParser = new StockParser();
        this.taxCalculator = new TaxCalculator();
        this.taxParser = new TaxParser();
    }

    @Override
    public String calculateTaxes() {
        var stockLines = stockReader.process(System.in);
        var stocks = stockParser.process(stockLines);
        var taxes = taxCalculator.process(stocks);
        var taxLines = taxParser.process(taxes);
        return taxLines;
    }

}

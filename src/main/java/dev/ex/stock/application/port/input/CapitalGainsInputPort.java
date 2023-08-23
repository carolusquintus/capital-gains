package dev.ex.stock.application.port.input;

import dev.ex.stock.application.usecase.CapitalGainsUseCase;
import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.service.StockParser;
import dev.ex.stock.domain.service.StockReader;
import dev.ex.stock.domain.service.shared.Service;

import java.io.InputStream;
import java.util.List;

public class CapitalGainsInputPort implements CapitalGainsUseCase {

    private Service<InputStream, List<String>> stockReader;
    private Service<List<String>, List<List<Stock>>> stockParser;

    public CapitalGainsInputPort() {
        this.stockReader = new StockReader();
        this.stockParser = new StockParser();
    }

    @Override
    public String calculateTaxes() {
        var lines = stockReader.process(System.in);
        var stocks = stockParser.process(lines);
        return stocks.toString();
    }

}

package dev.ex.stock.application.port.input;

import dev.ex.stock.application.usecase.CapitalGainsUseCase;
import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.service.StockParser;
import dev.ex.stock.domain.service.StockReader;
import dev.ex.stock.domain.service.shared.StockService;

import java.io.InputStream;
import java.util.List;

public class CapitalGainsInputPort implements CapitalGainsUseCase {

    private StockService<InputStream, String> reader;
    private StockService<List<String>, List<Stock>> parser;

    public CapitalGainsInputPort() {
        this.reader = new StockReader();
        this.parser = new StockParser();
    }

    @Override
    public String calculateTaxes() {
        var lines = reader.process(System.in);
        var stocks = parser.process(lines);
        return null;
    }

}

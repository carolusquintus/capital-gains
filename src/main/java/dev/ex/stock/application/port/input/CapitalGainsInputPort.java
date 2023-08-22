package dev.ex.stock.application.port.input;

import dev.ex.stock.application.usecase.CapitalGainsUseCase;
import dev.ex.stock.domain.service.StockReader;
import dev.ex.stock.domain.service.shared.StockService;

import java.io.InputStream;

public class CapitalGainsInputPort implements CapitalGainsUseCase {

    private StockService<InputStream, String> reader;

    public CapitalGainsInputPort() {
        this.reader = new StockReader();
    }

    @Override
    public String calculateTaxes() {
        var lines = reader.process(System.in);
        System.out.println(lines);
        return null;
    }

}

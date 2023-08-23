package dev.ex.stock.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.ex.stock.domain.entity.Stock;
import dev.ex.stock.domain.service.shared.StockService;
import io.vavr.control.Try;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;

public class StockParser implements StockService<List<String>, List<Stock>> {

    @Override
    public List<List<Stock>> process(List<String> lines) {
        var stocks = new ArrayList<List<Stock>>();
        var builder = JsonMapper.builder()
            .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
            .build();

        lines.forEach(line ->
            Try.of(() -> builder.readValue(line, new TypeReference<List<Stock>>() {}))
                .map(stocks::add));

        return stocks;
    }

}

package dev.ex.stock.domain.service;

import dev.ex.stock.domain.service.shared.StockService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class StockReader implements StockService<InputStream, String> {

    @Override
    public List<String> process(InputStream input) {
        var scan = new Scanner(input);

        var stockList = new ArrayList<String>();
        var builder = new StringBuilder();

        while (scan.hasNextLine()) {
            var endLine = scan.nextLine().trim();

            if ("".equals(endLine)) {
                break;
            }

            builder.append(endLine);

            if (endLine.endsWith("]")) {
                stockList.add(builder.toString());
                builder.delete(0, builder.length());
            }
        }

        return stockList;
    }
}

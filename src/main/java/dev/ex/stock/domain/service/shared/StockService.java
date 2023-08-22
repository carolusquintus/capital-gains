package dev.ex.stock.domain.service.shared;

import java.util.List;

public interface StockService<I, O> {

    List<O> process(I input);

}

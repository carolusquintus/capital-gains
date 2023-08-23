package dev.ex.stock.domain.service.shared;

public interface Service<I, O> {

    O process(I input);

}

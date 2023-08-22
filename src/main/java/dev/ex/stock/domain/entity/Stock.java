package dev.ex.stock.domain.entity;

import java.math.BigDecimal;

public record Stock(
    Operation operation,
    BigDecimal unitCost,
    Long quantity
) {}

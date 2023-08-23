package dev.ex.stock.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Stock(

    Operation operation,

    @JsonProperty("unit-cost")
    BigDecimal unitCost,

    Long quantity

) {}

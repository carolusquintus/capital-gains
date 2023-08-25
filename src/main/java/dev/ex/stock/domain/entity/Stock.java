package dev.ex.stock.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Stock(

    Operation operation,

    @JsonProperty("unit-cost")
    BigDecimal unitCost,

    Long quantity

) {

    public BigDecimal totalAmount() {
        return unitCost.multiply(new BigDecimal(quantity));
    }

    public boolean isLowerEqualTotalAmount(BigDecimal amount) {
        return totalAmount().compareTo(amount) <= 0;
    }

}

package dev.ex.stock.infra.input.cli;

import dev.ex.stock.application.port.input.CapitalGainsInputPort;

public class CapitalGainsCommand {

    public static void main(String[] args) {
        var useCase = new CapitalGainsInputPort();
        System.out.println(useCase.calculateTaxes());
    }
}
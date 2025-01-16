package com.automationtest.questions;

import com.automationtest.ui.CheckoutPage;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

@Slf4j
public class TotalWithTax implements Question<Boolean> {

    private final double expectedTotal;

    public TotalWithTax(double expectedTotal) {
        this.expectedTotal = expectedTotal;
    }

    public static TotalWithTax is(double expectedTotal) {
        return new TotalWithTax(expectedTotal);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        double itemTotal = Double.parseDouble(CheckoutPage.ITEM_TOTAL.resolveFor(actor).getText().replace("Item total: $", ""));
        double tax = Double.parseDouble(CheckoutPage.TAX.resolveFor(actor).getText().replace("Tax: $", ""));
        double displayedTotal = Double.parseDouble(CheckoutPage.TOTAL.resolveFor(actor).getText().replace("Total: $", ""));

        return Math.abs((itemTotal + tax) - displayedTotal) < 0.01;
    }
}

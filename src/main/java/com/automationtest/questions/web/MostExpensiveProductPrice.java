package com.automationtest.questions.web;

import com.automationtest.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostExpensiveProductPrice implements Question<Boolean> {
    private final Map<String, Double> expectedPrices;

    public MostExpensiveProductPrice(Map<String, Double> expectedPrices) {
        this.expectedPrices = expectedPrices;
    }

    public static MostExpensiveProductPrice matches(Map<String, Double> expectedPrices) {
        return new MostExpensiveProductPrice(expectedPrices);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<String> productNames = CheckoutPage.PRODUCT_NAMES.resolveAllFor(actor).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());

        List<Double> productPrices = CheckoutPage.PRODUCT_PRICES.resolveAllFor(actor).stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());

        double maxPrice = productPrices.stream().max(Double::compare).orElse(0.0);
        int maxPriceIndex = productPrices.indexOf(maxPrice);
        String mostExpensiveProduct = productNames.get(maxPriceIndex);

        return expectedPrices.containsKey(mostExpensiveProduct) &&
                expectedPrices.get(mostExpensiveProduct).equals(maxPrice);
    }
}

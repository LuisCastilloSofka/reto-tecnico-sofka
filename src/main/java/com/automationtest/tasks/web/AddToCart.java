package com.automationtest.tasks.web;


import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;
import java.util.stream.Collectors;

import static com.automationtest.ui.PageSauceProducts.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddToCart implements Task {

    public static AddToCart productsByCriteria() {
        return instrumented(AddToCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Obtain prices and buttons
        List<WebElementFacade> prices = PRODUCT_PRICES.resolveAllFor(actor);
        List<WebElementFacade> buttons = ADD_TO_CART_BUTTONS.resolveAllFor(actor);

        // Convert prices to a number  list
        List<Double> priceValues = prices.stream()
                .map(price -> Double.parseDouble(price.getText().replace("$", "").trim()))
                .collect(Collectors.toList());

        // Identify the lowest, highest and any other price indices
        int indexOfLowest = priceValues.indexOf(priceValues.stream().min(Double::compareTo).orElseThrow());
        int indexOfHighest = priceValues.indexOf(priceValues.stream().max(Double::compareTo).orElseThrow());
        int indexOfAny = 1;

        // Add products to cart
        actor.attemptsTo(
                Click.on(buttons.get(indexOfLowest)), // Product with lowestPrice
                Click.on(buttons.get(indexOfHighest)), // Product with highest price
                Click.on(buttons.get(indexOfAny)), // random product
                Click.on(CART_BUTTON)
        );
    }
}

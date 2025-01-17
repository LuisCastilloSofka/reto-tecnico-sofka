package com.automationtest.questions.web;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;


@Slf4j
public class SelectedProductsInCart implements Question<Boolean> {

    private final List<String> expectedProducts;

    public SelectedProductsInCart(List<String> expectedProducts) {
        this.expectedProducts = expectedProducts;
    }

    public static SelectedProductsInCart are(List<String> expectedProducts) {
        return new SelectedProductsInCart(expectedProducts);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Define a Target for the products in the cart
        Target CART_PRODUCTS = Target.the("Products in the cart")
                .locatedBy(".cart_item .inventory_item_name");

        // Get the names of the products in the cart
        List<String> cartProducts = CART_PRODUCTS.resolveAllFor(actor).stream()
                .map(WebElementFacade::getText)
                .toList();

        // Check if the expected products are in the cart
        log.info("ACAAAAAAAAAAAAAA" + cartProducts);
        return cartProducts.containsAll(expectedProducts);
    }
}

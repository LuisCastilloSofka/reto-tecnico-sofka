package com.automationtest.tasks.web;

import com.automationtest.ui.PageSauceCart;
import com.automationtest.ui.PageSauceProducts;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.automationtest.ui.PageSauceProducts.CART_BUTTON;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@Slf4j
public class AddProducts implements Task {

    private final List<String> productNames;

    public AddProducts(List<String> productNames) {
        this.productNames = productNames;
    }

    public static AddProducts toCart(List<String> productNames) {
        return instrumented(AddProducts.class, productNames);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String productName : productNames) {
            actor.attemptsTo(Click.on(PageSauceProducts.ADD_TO_CART_BUTTON.of(productName))
            );
        }
        actor.attemptsTo(
                Click.on(CART_BUTTON),
                Click.on(PageSauceCart.CHECKOUT_BUTTON)
        );
    }
}
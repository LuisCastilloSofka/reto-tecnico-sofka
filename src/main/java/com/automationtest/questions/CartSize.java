package com.automationtest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class CartSize  implements Question<Integer> {

    private final Target CART_ITEMS;

    public CartSize(Target cartItems){
        this.CART_ITEMS = cartItems;
    }

    public static CartSize totalItems(Target cartItems){
        return new CartSize(cartItems);
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return CART_ITEMS.resolveAllFor(actor).size();
    }
}

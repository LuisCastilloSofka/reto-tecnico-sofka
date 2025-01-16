package com.automationtest.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PageSauceCart {

    public static final Target REMOVE_BUTTONS = Target.the("Remove buttons in cart").locatedBy(".cart_item .cart_button");
    public static final Target CHECKOUT_BUTTON = Target.the("Checkout button").locatedBy("#checkout");

}


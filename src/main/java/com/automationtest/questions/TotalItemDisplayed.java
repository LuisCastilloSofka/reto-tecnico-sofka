package com.automationtest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import com.automationtest.ui.CheckoutPage;

public class TotalItemDisplayed implements Question {

    @Override
    public Integer answeredBy(Actor actor) {
        return CheckoutPage.TOTAL_ITEMS.resolveAllFor(actor).size();
    }

    public static TotalItemDisplayed inTheCart() {
        return new TotalItemDisplayed();
    }

}

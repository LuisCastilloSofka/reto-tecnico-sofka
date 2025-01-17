package com.automationtest.questions.web;

import com.automationtest.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class SuccessMessageDisplayed implements Question<Boolean> {

    public static SuccessMessageDisplayed isVisible() {
        return new SuccessMessageDisplayed();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return CheckoutPage.SUCCESS_MESSAGE.resolveFor(actor).isVisible();
    }
}

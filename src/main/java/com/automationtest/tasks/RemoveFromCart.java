package com.automationtest.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.automationtest.ui.PageSauceCart.REMOVE_BUTTONS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RemoveFromCart implements Task {

    public static RemoveFromCart anyProduct(){
       return instrumented(RemoveFromCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        List<WebElementFacade> removeButtons = REMOVE_BUTTONS.resolveAllFor(actor);

        if(!removeButtons.isEmpty()){
            actor.attemptsTo(
                    Click.on(removeButtons.get(0))
            );
        }

    }
}

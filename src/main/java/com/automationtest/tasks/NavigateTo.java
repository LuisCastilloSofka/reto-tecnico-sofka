package com.automationtest.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateTo implements Task {

    private final Class<? extends PageObject> targetPageClass;

    public NavigateTo(Class<? extends PageObject> targetPageClass) {
        this.targetPageClass = targetPageClass;
    }

    public static NavigateTo page(Class<? extends PageObject> targetPageClass){
        return instrumented(NavigateTo.class,targetPageClass);
    }

    @Step("{0} nagivates to page")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(targetPageClass)
        );

    }
}

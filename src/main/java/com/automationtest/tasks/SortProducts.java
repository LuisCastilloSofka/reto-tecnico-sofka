package com.automationtest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import static com.automationtest.ui.PageSauceProducts.SORT_DROPDOWN;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SortProducts implements Task {

    private final String sortOption;

    public SortProducts(String sortOption) {
        this.sortOption = sortOption;
    }

    public static SortProducts by(String sortOption) {
        return instrumented(SortProducts.class, sortOption);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(sortOption).from(SORT_DROPDOWN)
        );
    }
}

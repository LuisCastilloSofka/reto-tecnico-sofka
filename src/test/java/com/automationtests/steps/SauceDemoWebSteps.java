package com.automationtests.steps;

import com.automationtest.tasks.NavigateTo;
import com.automationtest.ui.*;
import com.automationtest.utils.templates.EnvironmentConfig;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.GivenWhenThen.*;

public class SauceDemoWebSteps {

    EnvironmentConfig environmentConfig = new EnvironmentConfig();


    @Given("customer {actor} navigates to sauceDemo page")
    public void customerNavigatestoSauceDemoPage(Actor actor){
        givenThat(actor).attemptsTo(
                NavigateTo.page(PageSwagLabs.class)
        );

    }
}

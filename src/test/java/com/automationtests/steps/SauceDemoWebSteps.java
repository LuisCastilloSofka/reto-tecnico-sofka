package com.automationtests.steps;

import com.automationtest.ui.*;
import com.automationtest.tasks.*;
import com.automationtest.utils.templates.EnvironmentConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class SauceDemoWebSteps {

    EnvironmentConfig environmentConfig = new EnvironmentConfig();


    @Given("customer {actor} navigates to sauceDemo page")
    public void customerNavigatestoSauceDemoPage(Actor actor){
        givenThat(actor).attemptsTo(
                Open.browserOn().the(PageSwagLabs.class)
        );
    }

    @When("login with credentials")
    public void loginWithCredentials(DataTable credentials) {
        List<Map<String,String>> rows = credentials.asMaps(String.class,String.class);
        for(Map<String,String> columns : rows){
            when(theActorInTheSpotlight()).wasAbleTo(
                    MakeLogin.withCredentials(columns.get("user"), columns.get("password"))
            );
        }
    }

    @Then("should login and see available products")
    public void shouldLoginAndSeeAvailableProducts(){
        then(theActorInTheSpotlight()).should(
                seeThat(the(PageSauceProducts.PRODUCT_TITLE),isVisible())
        );
    }

}

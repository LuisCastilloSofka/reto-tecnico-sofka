package com.automationtests.steps.api;

import com.automationtest.questions.api.ResponseCode;
import com.automationtest.questions.api.ValidResponseSchema;
import com.automationtest.tasks.api.CreateBooking;
import com.automationtest.tasks.api.CreateToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class RestfulBookerApiSteps {

    @Given("the {actor} connect to API {string}")
    public void queActorSeConectaALaApi(Actor actor, String urlBase) {
        givenThat(actor).whoCan(
                CallAnApi.at(urlBase)
        );
    }

    @When("the user creates a booking with the following details:")
    public void theUserCreatesABookingWithTheFollowingDetails(String bookingDetails) {
        theActorInTheSpotlight().attemptsTo(
                CreateBooking.withDetails(bookingDetails)
        );
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedCode) {
        theActorInTheSpotlight().should(
                seeThat("Response Code", ResponseCode.is(), equalTo(expectedCode))
        );
    }

    @Then("the response schema should match {string}")
    public void theResponseSchemaShouldMatch(String schemaPath) {
        theActorInTheSpotlight().should(
                seeThat("Response Schema", ValidResponseSchema.matches(schemaPath))
        );
    }

}

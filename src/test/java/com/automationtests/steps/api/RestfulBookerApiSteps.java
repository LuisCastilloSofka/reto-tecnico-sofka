package com.automationtests.steps.api;

import com.automationtest.questions.api.ResponseCode;
import com.automationtest.questions.api.ValidResponseSchema;
import com.automationtest.tasks.UpdateBooking;
import com.automationtest.tasks.api.CreateBooking;
import com.automationtest.tasks.api.CreateToken;
import com.automationtest.tasks.api.GetBooking;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class RestfulBookerApiSteps {

    @Given("the {actor} connect to API {string}")
    public void userConnectToTheApi(Actor actor, String urlBase) {
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

    @Given("a booking exists with ID {int}")
    public void aBookingExistsWithID(int id) {
        // Opcional: puedes hacer un setup para asegurar que el booking existe
    }

    @Given("a booking does not exist with ID {int}")
    public void aBookingDoesNotExistWithID(int id) {

    }

    @When("the user retrieves the booking with ID {int}")
    public void theUserRetrievesTheBookingWithID(int id) {
        theActorInTheSpotlight().attemptsTo(GetBooking.withId(id));
    }

    @Given("the user is authenticated with username {word} and password {word}")
    public void theUserIsAuthenticatedWithUsernameAndPassword(String username, String password){
        theActorInTheSpotlight().attemptsTo(
                CreateToken.withCredentials(username,password)
        );
    }

    @When("the user updates the booking with the following details:")
    public void theUserUpdatesTheBookingWithTheFollowingDetails(DataTable table) {
        // Obtener los detalles de la reserva desde el DataTable
        Map<String, String> bookingDetails = table.asMap(String.class, String.class);

        // Llamar a la tarea UpdateBooking con los detalles
        theActorInTheSpotlight().attemptsTo(
                UpdateBooking.withDetails(
                        "1",  // ID de la reserva
                        bookingDetails.get("firstname"),
                        bookingDetails.get("lastname"),
                        Integer.parseInt(bookingDetails.get("totalprice")),
                        Boolean.parseBoolean(bookingDetails.get("depositpaid")),
                        bookingDetails.get("checkin"),
                        bookingDetails.get("checkout"),
                        bookingDetails.get("additionalneeds")
                )
        );
    }

}

package com.automationtest.tasks;

import com.automationtest.tasks.api.CreateBooking;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.http.HttpStatus;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@Slf4j
public class UpdateBooking implements Task {

    private final String bookingId;
    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public UpdateBooking(String bookingId, String firstname, String lastname, int totalprice, boolean depositpaid,
                         String checkin, String checkout, String additionalneeds) {
        this.bookingId = bookingId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }

    public static UpdateBooking withDetails(String bookingId, String firstname, String lastname, int totalprice, boolean depositpaid,
                                            String checkin, String checkout, String additionalneeds) {
        return instrumented(UpdateBooking.class, bookingId, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String token = actor.recall("token");

        String requestBody = String.format("{\"firstname\": \"%s\", \"lastname\": \"%s\", \"totalprice\": %d, \"depositpaid\": %b, \"bookingdates\": {\"checkin\": \"%s\", \"checkout\": \"%s\"}, \"additionalneeds\": \"%s\"}",
                firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);

        SerenityRest.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .log().all()
                .contentType("application/json")
                .header("Cookie", token)
                .body(requestBody)
                .when()
                .put("/booking/1")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

    }
}

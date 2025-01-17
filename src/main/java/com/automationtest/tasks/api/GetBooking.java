package com.automationtest.tasks.api;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@Slf4j
public class GetBooking implements Task {

    private final int bookingId;

    public GetBooking(int bookingId){
        this.bookingId = bookingId;
    }

    public static GetBooking withId(int bookingId){
        return instrumented(GetBooking.class, bookingId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        SerenityRest.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .log().all()
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .log().all();
    }
}

package com.automationtest.tasks.api;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBooking implements Task {

    private final String bookingDetails;

    public CreateBooking(String bookingDetails){
        this.bookingDetails = bookingDetails;
    }

    public static CreateBooking withDetails(String bookingDetails){
        return instrumented(CreateBooking.class,bookingDetails);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/booking")
                        .with(request -> request
                                .contentType("application/json")
                                .body(bookingDetails))
        );
    }
}

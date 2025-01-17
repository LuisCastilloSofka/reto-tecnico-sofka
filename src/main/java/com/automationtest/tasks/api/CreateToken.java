package com.automationtest.tasks.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateToken implements Task {

    private final String username;
    private final String password;

    public CreateToken(String username,String password){
        this.username = username;
        this.password = password;
    }

    public static CreateToken withCredentials(String username,String password){
        return instrumented(CreateToken.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/auth")
                        .with(request -> request
                                .contentType("application/json")
                                .log().all()
                                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                        )
        );

        String token = SerenityRest.lastResponse().jsonPath().getString("token");
        actor.remember("token","token=" + token);
    }
}

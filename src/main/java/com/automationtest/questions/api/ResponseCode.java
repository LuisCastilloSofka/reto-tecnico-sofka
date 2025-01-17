package com.automationtest.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseCode implements Question<Integer> {

    public static ResponseCode is() {
        return new ResponseCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        SerenityRest.lastResponse().then().log().all();
        return SerenityRest.lastResponse().statusCode();
    }
}

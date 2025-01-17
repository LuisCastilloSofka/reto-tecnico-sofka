package com.automationtest.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ValidResponseSchema implements Question<Boolean> {

    private final String schemaPath;

    public ValidResponseSchema(String schemaPath){
        this.schemaPath = schemaPath;
    }

    public static ValidResponseSchema matches(String schemaPath){
        return new ValidResponseSchema(schemaPath);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        SerenityRest.lastResponse().then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
        return true;
    }
}

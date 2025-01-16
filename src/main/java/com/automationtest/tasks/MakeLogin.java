package com.automationtest.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.automationtest.ui.PageSwagLabs.*;


public class MakeLogin implements Task {

    private final String user;
    private final String pass;

    public MakeLogin(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public static MakeLogin withCredentials(String user, String pass) {
        return Tasks.instrumented(MakeLogin.class, user, pass);
    }

    @Step("{0} inicia sesión:")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Task.where("Ingresa las credenciales e inicia sesión",
                        Enter.keyValues(this.user).into(INPUT_USERNAME),
                        Enter.keyValues(this.pass).into(INPUT_PASSWORD),
                        Click.on(BUTTON_LOGIN)
                )
        );
    }
}
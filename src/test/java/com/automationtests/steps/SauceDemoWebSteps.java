package com.automationtests.steps;

import com.automationtest.questions.ProductsSortedByPrice;
import com.automationtest.questions.SelectedProductsInCart;
import com.automationtest.ui.*;
import com.automationtest.tasks.*;
import com.automationtest.utils.templates.EnvironmentConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static com.automationtest.tasks.SortProducts.by;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.is;


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

    @When("the user sorts the products by {string}")
    public void theUserSortsTheProductsBy(String sortOption){
        OnStage.theActorInTheSpotlight().attemptsTo(
                by(sortOption)
        );
    }

    @Given("the user is logged into Sauce Demo")
    public void theUserIsLoggedIntoSauceDemo(){
        when(theActorInTheSpotlight()).wasAbleTo(
                MakeLogin.withCredentials("standard_user","secret_sauce")
        );
    }

    @Then("the products should be displayed in ascending order of price")
    public void theProductShouldBeDisplayedInAscendingOrderOfPrice(){
        OnStage.theActorInTheSpotlight().should(
                seeThat(ProductsSortedByPrice.inAscendingOrder(), is(true))
        );
    }

    @When("the user adds the products with the lowest, highest, and any price to the cart")
    public void theUserAddsTheProductsToTheCart(){
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddToCart.productsByCriteria()
        );
    }

    @Then("the selected products should be in the cart")
    public void theSelectedProductsShouldBeInTheCart() {
        List<String> expectedProducts = List.of("Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Sauce Labs Bike Light");
        OnStage.theActorInTheSpotlight().should(
                    seeThat(SelectedProductsInCart.are(expectedProducts), is(true))
            );
        }

}

package com.automationtests.steps;

import com.automationtest.questions.*;
import com.automationtest.ui.*;
import com.automationtest.tasks.*;
import com.automationtest.utils.templates.EnvironmentConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static com.automationtest.tasks.SortProducts.by;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class SauceDemoWebSteps {

    EnvironmentConfig environmentConfig = new EnvironmentConfig();

    private int initialCartSize;

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

    @Given("the user has added products to the cart")
    public void theUserHasAddedProductsToTheCart(){
        when(theActorInTheSpotlight()).attemptsTo(
                MakeLogin.withCredentials("standard_user","secret_sauce"),
                AddToCart.productsByCriteria()
        );

        initialCartSize = OnStage.theActorInTheSpotlight().asksFor(
                CartSize.totalItems(PageSauceCart.REMOVE_BUTTONS)
        );
    }

    @When("the user removes a product from the cart")
    public void theUserRemovesAProductFromTheCart(){
        OnStage.theActorInTheSpotlight().attemptsTo(
                RemoveFromCart.anyProduct()
        );
    }

    @Then("the product should no longer be in the cart")
    public void theProductShouldNotLongerBeInTheCart(){

        int updatedCartSize = OnStage.theActorInTheSpotlight().asksFor(
                CartSize.totalItems(PageSauceCart.REMOVE_BUTTONS)
        );

        Assert.assertTrue(updatedCartSize<initialCartSize);
    }

    @Given("the user proceeds to checkout")
    public void theUserProceedsToCheckout(){
        when(theActorInTheSpotlight()).attemptsTo(
                MakeLogin.withCredentials("standard_user","secret_sauce"),
                AddToCart.productsByCriteria(),
                Click.on(PageSauceCart.CHECKOUT_BUTTON)
        );
    }

    @When("the user completes the checkout process with {string}, {string}, and {string}")
    public void theUserCompletesTheCheckoutProcess(String firstName, String lastName, String postalCode) {
        theActorInTheSpotlight().attemptsTo(
                CompleteCheckoutForm.with(firstName, lastName, postalCode)
        );
    }

    @Then("the total number of items in the cart should be {int}")
    public void theTotalNumberOfItemsInTheCartShouldBe(int expectedItems) {
        theActorInTheSpotlight().should(
                seeThat("Total items in cart", TotalItemDisplayed.inTheCart(), equalTo(expectedItems))
        );
    }

    @Then("the total with tax should be correct based on {double}")
    public void theTotalWithTaxShouldBeCorrect(double expectedTotal) {
        theActorInTheSpotlight().should(
                seeThat("Total with tax is correct", TotalWithTax.is(expectedTotal))
        );
        theActorInTheSpotlight().attemptsTo(
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }

    @Then("the success message should be visible")
    public void theSuccessMessageShouldBeVisible() {
        theActorInTheSpotlight().should(
                seeThat("Success message is visible", SuccessMessageDisplayed.isVisible())
        );
    }

    @Given("the user has added the following products to the cart:")
    public void theUserHasAddedTheFollowingProductsToTheCart(List<String> productNames) {
        theActorInTheSpotlight().attemptsTo(
                MakeLogin.withCredentials("standard_user","secret_sauce"),
                AddProducts.toCart(productNames)
        );
    }

    @Then("the price of the most expensive product should match the expected price")
    public void thePriceOfTheMostExpensiveProductShouldMatch(DataTable expectedPrices) {
        Map<String, Double> expectedPriceMap = expectedPrices.asMap(String.class, Double.class);

        theActorInTheSpotlight().should(
                seeThat(
                        "The price of the most expensive product",
                        MostExpensiveProductPrice.matches(expectedPriceMap),
                        equalTo(true)
                )
        );
    }








}

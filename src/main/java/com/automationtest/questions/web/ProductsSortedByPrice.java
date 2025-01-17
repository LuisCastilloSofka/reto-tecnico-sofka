package com.automationtest.questions.web;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

import static com.automationtest.ui.PageSauceProducts.PRODUCT_PRICES;

@Slf4j
public class ProductsSortedByPrice implements Question<Boolean> {

    public static ProductsSortedByPrice inAscendingOrder(){
        return new ProductsSortedByPrice();
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        List<WebElementFacade> prices = PRODUCT_PRICES.resolveAllFor(actor);
        List<Double> priceValues = prices.stream()
                .map(price -> Double.parseDouble(price.getText().replace("$","")))
                .toList();
        return priceValues.equals(priceValues.stream().sorted().toList());
    }
}

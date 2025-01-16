package com.automationtest.ui;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;


@DefaultUrl("page:webdriver.base.url.swaglabs")
public class PageSwagLabs extends PageObject {

    public static final Target INPUT_USERNAME = Target.the("User input").locatedBy("#user-name");
    public static final Target INPUT_PASSWORD = Target.the("password input").locatedBy("#password");
    public static final Target BUTTON_LOGIN = Target.the("Login button").locatedBy("#login-button");
    public static final Target MESSAGE_LOCKED_ERROR = Target.the("error mesagge for blocked users").locatedBy("//div/h3[@data-test='error']");
    public static final Target LABEL_PRODUCT = Target.the("Product list label").locatedBy("//span[contains(text(), 'Products')]");


}

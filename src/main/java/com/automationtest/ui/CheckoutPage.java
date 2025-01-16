package com.automationtest.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    public static final Target FIRST_NAME_FIELD = Target.the("First name field").locatedBy("#first-name");
    public static final Target LAST_NAME_FIELD = Target.the("Last name field").locatedBy("#last-name");
    public static final Target POSTAL_CODE_FIELD = Target.the("Postal code field").locatedBy("#postal-code");
    public static final Target CONTINUE_BUTTON = Target.the("Continue button").locatedBy("#continue");
    public static final Target TOTAL_ITEMS = Target.the("Total items in the cart").locatedBy(".cart_item");
    public static final Target ITEM_TOTAL = Target.the("Item total").locatedBy(".summary_subtotal_label");
    public static final Target TAX = Target.the("Tax").locatedBy(".summary_tax_label");
    public static final Target TOTAL = Target.the("Total price").locatedBy(".summary_total_label");
    public static final Target SUCCESS_MESSAGE = Target.the("Success message").locatedBy(".complete-header");
    public static final Target FINISH_BUTTON = Target.the("Finish button").locatedBy("#finish");
    public static final Target PRODUCT_NAMES = Target.the("Product names").locatedBy(".inventory_item_name");
    public static final Target PRODUCT_PRICES = Target.the("Product prices").locatedBy(".inventory_item_price");






}

package com.automationtest.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PageSauceProducts {

    private PageSauceProducts() {
    }

    public static final Target PRODUCT_TITLE = Target.the("'Product list title'").locatedBy("//span[contains(text(), 'Products')]");
    public static final Target SORT_DROPDOWN = Target.the("Sort dropdown").locatedBy(".product_sort_container");
    public static final Target PRODUCT_PRICES = Target.the("Product prices").locatedBy(".inventory_item_price");
    public static final Target PRODUCT_NAMES = Target.the("Product names").locatedBy(".inventory_item_name");
    public static final Target ADD_TO_CART_BUTTONS = Target.the("Add to cart buttons").locatedBy(".inventory_item .btn_inventory");
    public static final Target CART_BUTTON = Target.the("Cart button").locatedBy(".shopping_cart_link");
}



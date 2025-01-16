package com.automationtest.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PageSauceProducts {

    private PageSauceProducts() {
    }

    public static final Target PRODUCT_TITLE = Target.the("'Product list title'").locatedBy("//span[contains(text(), 'Products')]");
    public static final Target SORT_DROPDOWN = Target.the("Sort dropdown").locatedBy(".product_sort_container");
    public static final Target PRODUCT_PRICES = Target.the("Product prices").locatedBy(".inventory_item_price");
}

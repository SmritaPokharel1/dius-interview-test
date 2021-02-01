package com.shoppingcart.app;

import com.shoppingcart.checkout.domain.Item;
import com.shoppingcart.checkout.service.CheckoutService;
import com.shoppingcart.checkout.service.CheckoutServiceImpl;
import com.shoppingcart.rules.domain.BulkDealRule;
import com.shoppingcart.rules.domain.BulkDiscountRule;
import com.shoppingcart.rules.domain.BundleRule;
import com.shoppingcart.rules.domain.PricingRule;
import com.shoppingcart.rules.service.RulesEngine;
import com.shoppingcart.rules.service.RulesEngineImpl;

import java.util.Collections;

public class ShoppingCartApp {

    public static void main(String[] args) {

        RulesEngine rulesEngine = new RulesEngineImpl();

        PricingRule bulkDealRule = new BulkDealRule("atv", 3, 1);
        PricingRule bulkDiscountRule = new BulkDiscountRule("ipd", 499.99, 4);
        BundleRule bundleRule = new BundleRule("mbp");
        bundleRule.setBundledItemsSkus(Collections.singletonList("vga"));

        rulesEngine.addRule(bulkDealRule);
        rulesEngine.addRule(bulkDiscountRule);
        rulesEngine.addRule(bundleRule);

        CheckoutService checkout = new CheckoutServiceImpl(rulesEngine.getPricingRules());

        Item macbook = new Item("mbp", 1399.99);
        Item ipad = new Item("ipd", 549.99);
        Item atv = new Item("atv", 109.50);
        Item vga = new Item("vga", 30.00);

        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(vga);

        System.out.println(checkout.total());
    }


}

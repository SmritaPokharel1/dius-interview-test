package com.shoppingcart.checkout.service;

import com.shoppingcart.checkout.domain.Item;
import com.shoppingcart.rules.domain.BulkDealRule;
import com.shoppingcart.rules.domain.BulkDiscountRule;
import com.shoppingcart.rules.domain.BundleRule;
import com.shoppingcart.rules.domain.PricingRule;
import com.shoppingcart.rules.service.RulesEngine;
import com.shoppingcart.rules.service.RulesEngineImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    CheckoutService checkoutService;

    @BeforeEach
    void setup() {
        RulesEngine rulesEngine = new RulesEngineImpl();

        PricingRule bulkDealRule = new BulkDealRule("atv", 3, 1);
        PricingRule bulkDiscountRule = new BulkDiscountRule("ipd", 499.99, 4);
        BundleRule bundleRule = new BundleRule("mbp");
        bundleRule.setBundledItemsSkus(Collections.singletonList("vga"));

        rulesEngine.addRule(bulkDealRule);
        rulesEngine.addRule(bulkDiscountRule);
        rulesEngine.addRule(bundleRule);

        checkoutService = new CheckoutServiceImpl(rulesEngine.getPricingRules());
    }

    @Test
    void givenCheckoutService_when_3_atv_and_1_vga_are_scanned_expect_total_price_to_be_249() {


        checkoutService.scan(new Item("atv", 109.50));
        checkoutService.scan(new Item("atv", 109.50));
        checkoutService.scan(new Item("atv", 109.50));
        checkoutService.scan(new Item("vga", 30.00));

        double total = checkoutService.total();

        assertEquals(249.00, total);
    }

    @Test
    void givenCheckoutService_when_2_atv_and_5_ipd_are_scanned_expect_total_price_to_be_2718_dollars_and_95_cents() {

        checkoutService.scan(new Item("atv", 109.50));
        checkoutService.scan(new Item("ipd", 549.99));
        checkoutService.scan(new Item("ipd", 549.99));
        checkoutService.scan(new Item("atv", 109.50));
        checkoutService.scan(new Item("ipd", 549.99));
        checkoutService.scan(new Item("ipd", 549.99));
        checkoutService.scan(new Item("ipd", 549.99));

        double total = checkoutService.total();

        assertEquals(2718.95, total);
    }

    @Test
    void givenCheckoutService_when_1_mpd_and_1_vga_and_1_ipd_are_scanned_expect_total_price_to_be_1949_dollars_and_98_cents() {

        checkoutService.scan(new Item("mbp", 1399.99));
        checkoutService.scan(new Item("vga", 30.00));
        checkoutService.scan(new Item("ipd", 549.99));

        double total = checkoutService.total();

        assertEquals(1949.98, total);
    }

    @AfterEach
    void tearDown() {

    }

}

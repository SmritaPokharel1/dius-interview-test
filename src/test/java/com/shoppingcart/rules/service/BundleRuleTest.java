package com.shoppingcart.rules.service;

import com.shoppingcart.rules.domain.BundleRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleRuleTest {

    BundleRule bundleRule;

    @BeforeEach
    void setup() {
        bundleRule = new BundleRule("mbp");
    }

    @Test
    void givenBundleRule_when_two_macs_are_bundled_with_two_vgas_then_set_vgaQuantity_to_0() {
        Map<String, Integer> skuQuantityMap = new HashMap<String, Integer>();

        skuQuantityMap.put("mbp", 2);
        skuQuantityMap.put("vga", 2);

        bundleRule.setSkuQuantityMap(skuQuantityMap);
        bundleRule.setBundledItemsSkus(Arrays.asList(new String[]{"vga"}));
        bundleRule.applyRule();

        assertEquals(0, skuQuantityMap.get("vga"));
    }

    @Test
    void givenBundleRule_when_1_macs_is_bundled_with_2_vga_then_set_vgaQuantity_to_1() {
        Map<String, Integer> skuQuantityMap = new HashMap<String, Integer>();

        skuQuantityMap.put("mbp", 1);
        skuQuantityMap.put("vga", 2);

        bundleRule.setSkuQuantityMap(skuQuantityMap);
        bundleRule.setBundledItemsSkus(Arrays.asList(new String[]{"vga"}));
        bundleRule.applyRule();

        assertEquals(1, skuQuantityMap.get("vga"));
    }

    @AfterEach
    void tearDown() {

    }
}

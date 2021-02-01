package com.shoppingcart.rules.service;

import com.shoppingcart.rules.domain.BulkDiscountRule;
import com.shoppingcart.rules.domain.PricingRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulkDiscountRuleTest {

    PricingRule pricingRule;
    Map<String,Double> skuPriceMap;
    Map<String,Integer> skuQuantityMap;

    @BeforeEach
    void setup(){
        pricingRule = new BulkDiscountRule("ipd",499.99,4);
        skuPriceMap = new HashMap<String,Double>();
        skuQuantityMap = new HashMap<String,Integer>();
    }

    @Test
    void givenBulkDiscountRule_when_1_ipad_costing_599_is_scanned_then_keep_ipad_price_as_599(){
        skuPriceMap.put("ipd",599.99);
        skuQuantityMap.put("ipd",1);
        pricingRule.setSkuPriceMap(skuPriceMap);
        pricingRule.setSkuQuantityMap(skuQuantityMap);
        pricingRule.applyRule();

        assertEquals(599.99,skuPriceMap.get("ipd"));

    }

    @Test
    void givenBulkDiscountRule_when_5_ipads_costing_599_are_scanned_then_set_each_ipad_price_as_499(){
        skuPriceMap.put("ipd",599.99);
        skuQuantityMap.put("ipd",5);
        pricingRule.setSkuPriceMap(skuPriceMap);
        pricingRule.setSkuQuantityMap(skuQuantityMap);
        pricingRule.applyRule();

        assertEquals(499.99,skuPriceMap.get("ipd"));

    }

    @AfterEach
    void tearDown(){

    }

}

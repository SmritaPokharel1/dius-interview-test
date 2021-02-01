package com.shoppingcart.rules.service;

import com.shoppingcart.rules.domain.BulkDealRule;
import com.shoppingcart.rules.domain.PricingRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulkDealRuleTest {

    private PricingRule pricingRule;
    Map<String,Double> skuPriceMap;
    Map<String,Integer> skuQuantityMap;

    @BeforeEach
    void setup(){

        pricingRule = new BulkDealRule("atv",3,1);
        skuPriceMap = new HashMap<String,Double>();
        skuQuantityMap = new HashMap<String,Integer>();
    }

    @Test
    void givenBulkDealRule_when_three_AppleTv_are_scanned_then_set_new_AppleTV_quantity_to_2(){
        skuPriceMap.put("atv",1399.00);
        skuQuantityMap.put("atv",3);

        pricingRule.setSkuPriceMap(skuPriceMap);
        pricingRule.setSkuQuantityMap(skuQuantityMap);
        pricingRule.applyRule();

        assertEquals(2,skuQuantityMap.get("atv"));

    }

    @AfterEach
    void tearDown(){

    }
}

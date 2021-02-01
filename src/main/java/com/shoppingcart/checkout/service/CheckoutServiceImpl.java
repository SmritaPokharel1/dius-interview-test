package com.shoppingcart.checkout.service;

import com.shoppingcart.checkout.domain.Item;
import com.shoppingcart.rules.domain.PricingRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutServiceImpl implements CheckoutService {

    private Map<String, Integer> skuQuantityMap;
    private Map<String, Double> skuPriceMap;
    private List<PricingRule> pricingRules;
    double total = 0;

    public CheckoutServiceImpl(List<PricingRule> pricingRules) {
        skuQuantityMap = new HashMap<>();
        skuPriceMap = new HashMap<>();
        this.pricingRules = pricingRules;
    }

    @Override
    public void scan(Item item) {
        skuPriceMap.put(item.getItemCode(), item.getUnitPrice());
        skuQuantityMap.merge(item.getItemCode(), 1, Integer::sum);
    }

    @Override
    public double total() {
        if (skuQuantityMap.isEmpty() || skuPriceMap.isEmpty()) {
            return 0;
        }
        for(PricingRule rule : pricingRules){
            rule.setSkuQuantityMap(skuQuantityMap);
            rule.setSkuPriceMap(skuPriceMap);

            rule.applyRule();
        }

        for (String itemCode : skuQuantityMap.keySet()) {
                total += skuQuantityMap.get(itemCode) * skuPriceMap.get(itemCode);
        }

        return total;
    }
}

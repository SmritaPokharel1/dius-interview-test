package com.shoppingcart.rules.domain;

import java.util.Map;

public class BulkDiscountRule extends PricingRule {

    private final Double specialDropPrice;
    private final Integer quantityThreshold;

    public BulkDiscountRule(String owningItemSku, Double specialDropPrice, Integer quantityThreshold) {

        super(owningItemSku);
        this.specialDropPrice = specialDropPrice;
        this.quantityThreshold = quantityThreshold;
    }

    @Override
    public void applyRule() {
        if (skuQuantityMap.get(owningItemSku) != null) {
            if (skuQuantityMap.get(owningItemSku) > quantityThreshold) {
                skuPriceMap.put(owningItemSku, specialDropPrice);
            }
        }
    }

    public Map<String, Integer> getSkuQuantityMap() {
        return skuQuantityMap;
    }

    public void setSkuQuantityMap(Map<String, Integer> skuQuantityMap) {
        this.skuQuantityMap = skuQuantityMap;
    }

    public Map<String, Double> getSkuPriceMap() {
        return skuPriceMap;
    }

    public void setSkuPriceMap(Map<String, Double> skuPriceMap) {
        this.skuPriceMap = skuPriceMap;
    }
}

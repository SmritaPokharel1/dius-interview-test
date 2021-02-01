package com.shoppingcart.rules.domain;

import java.util.Map;

public abstract class PricingRule {

    protected Map<String, Integer> skuQuantityMap;
    protected String owningItemSku;
    protected Map<String, Double> skuPriceMap;

    public PricingRule(String owningItemSku) {
        this.owningItemSku = owningItemSku;
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

    public abstract void applyRule();


}

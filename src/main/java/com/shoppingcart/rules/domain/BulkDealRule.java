package com.shoppingcart.rules.domain;

public class BulkDealRule extends PricingRule {

    private final int quantityThreshold;
    private final int quantityToReduce;

    public BulkDealRule(String owningItemSku, int quantityThreshold, int quantityToReduce) {
        super(owningItemSku);
        this.quantityThreshold = quantityThreshold;
        this.quantityToReduce = quantityToReduce;
    }

    @Override
    public void applyRule() {
        if (skuQuantityMap.get(owningItemSku) != null) {
            int quantityBought = skuQuantityMap.get(owningItemSku);
            if (quantityBought >= quantityThreshold) {
                int newQuantity = quantityBought - quantityBought / quantityThreshold * quantityToReduce;
                skuQuantityMap.put(owningItemSku, newQuantity);
            }
        }
    }
}

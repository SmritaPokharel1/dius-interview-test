package com.shoppingcart.rules.domain;

import java.util.List;
import java.util.Map;

public class BundleRule extends PricingRule {

    private List<String> bundledItemsSkus;

    public BundleRule(String owningItemSku) {
        super(owningItemSku);
    }

    @Override
    public void applyRule() {

        if(!bundledItemsSkus.isEmpty()) {
            for (String sku : bundledItemsSkus) {
                if(skuQuantityMap.get(sku)!=null && skuQuantityMap.get(owningItemSku)!=null) {
                    if (skuQuantityMap.get(sku) != null && skuQuantityMap.get(sku) <= skuQuantityMap.get(owningItemSku)) {
                        skuQuantityMap.put(sku, 0);
                    } else if (skuQuantityMap.get(sku) != null && skuQuantityMap.get(sku) > skuQuantityMap.get(owningItemSku)) {
                        skuQuantityMap.put(sku, skuQuantityMap.get(sku) - skuQuantityMap.get(owningItemSku));
                    }
                }
            }
        }
    }

    public void setBundledItemsSkus(List<String> bundledItemsSkus) {
        this.bundledItemsSkus = bundledItemsSkus;
    }

    public void setSkuQuantityMap(Map<String, Integer> skuQuantityMap) {
        this.skuQuantityMap = skuQuantityMap;
    }

    public void setOwningItemSku(String owningItemSku) {
        this.owningItemSku = owningItemSku;
    }
}

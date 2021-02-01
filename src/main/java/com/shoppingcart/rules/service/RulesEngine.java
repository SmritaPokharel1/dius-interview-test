package com.shoppingcart.rules.service;

import com.shoppingcart.rules.domain.PricingRule;

import java.util.List;

public interface RulesEngine {

    List<PricingRule> getPricingRules();

    void addRule(PricingRule rule);
}

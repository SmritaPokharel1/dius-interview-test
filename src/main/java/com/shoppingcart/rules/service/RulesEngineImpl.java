package com.shoppingcart.rules.service;

import com.shoppingcart.rules.domain.PricingRule;

import java.util.ArrayList;
import java.util.List;

public class RulesEngineImpl implements RulesEngine{

    public List<PricingRule> pricingRules;

    public RulesEngineImpl(){

        pricingRules = new ArrayList<PricingRule>();
    }

    public void addRule(PricingRule rule){

        this.pricingRules.add(rule);
    }

    public List<PricingRule> getPricingRules(){

        return this.pricingRules;
    }


}

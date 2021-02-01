package com.shoppingcart.rules.service;

public interface PriceCalculator {

    double calculatePrice(int quantity, double unitPrice,int macQuantity);
}

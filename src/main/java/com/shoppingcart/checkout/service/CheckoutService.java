package com.shoppingcart.checkout.service;

import com.shoppingcart.checkout.domain.Item;

public interface CheckoutService {

    void scan(Item item);

    double total();
}

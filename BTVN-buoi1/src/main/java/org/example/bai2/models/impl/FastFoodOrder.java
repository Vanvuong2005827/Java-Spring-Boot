package org.example.bai2.models.impl;

import org.example.bai2.models.Order;
import org.springframework.stereotype.Component;

@Component
public class FastFoodOrder implements Order {
    @Override
    public void placeOrder() {
        System.out.println("Đặt món ăn nhanh");
    }
}

package org.example.bai2.models.impl;

import org.example.bai2.models.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Thanh toán bằng thẻ tin dụng...");
    }
}

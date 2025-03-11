package org.example.bai2.User;

import org.example.bai2.models.Order;
import org.example.bai2.models.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    private Order order;
    private PaymentMethod paymentMethod;

    @Autowired
    public Customer(@Qualifier("fastFoodOrder") Order order,@Qualifier("payPalPayment") PaymentMethod paymentMethod) {
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public void run() {
        order.placeOrder();
        paymentMethod.pay();
    }

}

package org.example.bai1.models.Impl;

import org.example.bai1.models.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
    @Override
    public void callName() {
        System.out.println("This is a car");
    }
}

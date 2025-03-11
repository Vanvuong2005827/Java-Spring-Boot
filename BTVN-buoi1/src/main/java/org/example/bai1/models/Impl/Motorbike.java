package org.example.bai1.models.Impl;

import org.example.bai1.models.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class Motorbike implements Vehicle {

    @Override
    public void callName() {
        System.out.println("this is a Motorbike");
    }
}

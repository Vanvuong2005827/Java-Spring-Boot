package org.example.bai1.models.Impl;

import org.example.bai1.models.Engine;
import org.springframework.stereotype.Component;

@Component
public class GasolineEngine implements Engine {

    @Override
    public void callName() {
        System.out.println("This vehicle using Gas as engine");
    }
}

package org.example.buoi1;


import org.springframework.stereotype.Component;

@Component

public class Iphone implements Phone {

    @Override
    public void call() {
        System.out.println("Calling Iphone");
    }
}

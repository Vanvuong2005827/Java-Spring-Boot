package org.example.buoi1;

import org.springframework.stereotype.Component;

@Component
public class Samsung implements Phone{

    @Override
    public void call() {
        System.out.println("Calling SamSung");
    }
}

package org.example.bai1.User;

import org.example.bai1.models.Engine;
import org.example.bai1.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class User {
    private Vehicle vehicle;
    private Engine engine;

    @Autowired
    public User(@Qualifier("car") Vehicle vehicle, @Qualifier("gasolineEngine") Engine engine) {
        this.vehicle = vehicle;
        this.engine = engine;
    }

    public void makeCall() {
        vehicle.callName();
        engine.callName();
    }
}

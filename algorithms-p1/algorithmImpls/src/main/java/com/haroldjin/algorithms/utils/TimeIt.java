package com.haroldjin.algorithms.utils;

import java.time.Duration;
import java.time.Instant;

public class TimeIt {
    private Instant previous;
    public TimeIt(){
       previous =  Instant.now();
    }

    public void printTimeElapsed(String name){
        Instant now = Instant.now();
        long millis = Duration.between(previous, now).toMillis();
        System.out.printf("%s: Time elapsed %s milliseconds\n", name, millis);
    }
}

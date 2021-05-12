package com.example.demo.service;

import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    private static AtomicReference<Singleton> singletonAtomicReference = new AtomicReference<>();

    public static Singleton getInstance() {
        for (;;) {
            Singleton singleton = singletonAtomicReference.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new Singleton();
            if (singletonAtomicReference.compareAndSet(null, singleton)) {
                return singleton;
            }
        }

    }

    private Singleton() {

    }

    public static void main(String[] args) {
       Singleton.getInstance();
    }
}

package com.example.demo.service;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Serializable {

    private int age;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }
}

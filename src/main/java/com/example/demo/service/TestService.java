package com.example.demo.service;

import com.example.demo.annotation.Receiver;
import com.example.demo.annotationclass.AnnotationRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestService {
    @Autowired
    private AnnotationRegister annotationRegister;

    public static TestService instance;

    private String testName;

    public boolean show;

    private TestService() {

    }

    @PostConstruct
    public void init() {
        annotationRegister.register(this);
    }

    @Receiver
    public void test() {
        System.out.println("hello");
    }

    @Receiver
    public void test1() {
        System.out.println("test");
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}

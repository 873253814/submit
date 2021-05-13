package com.example.demo.listen;

import org.springframework.stereotype.Component;

@Component
public class EventHome extends EventListenerAdapt{
    @Override
    public void eventStart() {
        System.out.println(this.getClass().toString());
    }
}

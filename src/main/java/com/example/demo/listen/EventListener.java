package com.example.demo.listen;

import com.example.demo.annotation.Listener;

public interface EventListener {
    @Listener
    public interface IEvent {
        void eventStart();
    }
}

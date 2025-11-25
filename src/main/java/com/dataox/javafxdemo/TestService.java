package com.dataox.javafxdemo;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String sendStartMessage() {
        return "Hello from test service";
    }
}

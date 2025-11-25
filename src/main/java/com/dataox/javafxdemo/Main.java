package com.dataox.javafxdemo;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.setProperty("javafx.application", "com.dataox.javafxdemo.JavaFxDemoApplication");
        Application.launch(JavaFxDemoApplication.class, args);
    }
}

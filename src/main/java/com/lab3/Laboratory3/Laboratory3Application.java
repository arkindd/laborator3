package com.lab3.Laboratory3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Laboratory3Application {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(Laboratory3Application.class, args);
        JView jView = new JView();
        jView.setVisible(true);
    }
}
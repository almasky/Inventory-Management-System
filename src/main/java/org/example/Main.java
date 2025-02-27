package org.example;

import org.example.config.HibernateConfig;


public class Main {
    public static void main(String[] args) {

        HibernateConfig.geSessionFactory();
        System.out.println("Hello World");

    }
}
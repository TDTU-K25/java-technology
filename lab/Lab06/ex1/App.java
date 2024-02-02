package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext aC = new ClassPathXmlApplicationContext("appConfig.xml");
        Product firstProduct = (Product) aC.getBean("firstProduct");
        System.out.println(firstProduct);
        Product secondProduct = (Product) aC.getBean("secondProduct");
        System.out.println(secondProduct);
        Product thirdProduct = (Product) aC.getBean("thirdProduct");
        System.out.println(thirdProduct);
    }
}

package com.venu.App2.Model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Laptop implements computer{

    @Override
    public void show(){

        System.out.println("Laptop method called from show");

    }
}

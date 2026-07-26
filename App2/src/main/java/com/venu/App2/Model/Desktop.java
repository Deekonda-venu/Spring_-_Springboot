package com.venu.App2.Model;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements computer{

    @Override
    public void show(){

        System.out.println("Desktop method called from show");

    }


}

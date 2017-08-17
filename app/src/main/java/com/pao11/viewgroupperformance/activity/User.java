package com.pao11.viewgroupperformance.activity;

/**
 * Created by pao11 on 2017/5/20.
 */
public class User implements MyInterface{
    String name ;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

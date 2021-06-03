package com.fanlu.springbootdemo;

import lombok.Data;

@Data
public class WebInfo {
    private String name;

    public WebInfo(String name){
        this.name = name;
    }
}

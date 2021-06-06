package io.kimmking.homework05;

import io.kimmking.aop.ISchool;

public class MySchool implements ISchool {

    @Override
    public void ding() {
        System.out.println("my school ding...");
    }
}

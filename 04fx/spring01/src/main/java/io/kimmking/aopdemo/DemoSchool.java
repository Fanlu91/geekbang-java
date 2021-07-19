package io.kimmking.aopdemo;

import io.kimmking.aop.ISchool;
import org.springframework.stereotype.Component;

@Component
public class DemoSchool implements ISchool {
    @Override
    public void ding() {
        System.out.println("demo school");
    }
}

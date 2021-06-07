package com.fanlu.springbootstarter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class School implements ISchool {

    // Resource 
    @Autowired()
    Klass class1;

    @Override
    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size());

    }

}

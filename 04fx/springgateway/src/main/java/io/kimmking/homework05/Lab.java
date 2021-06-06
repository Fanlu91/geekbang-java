package io.kimmking.homework05;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component("myLab")
public class Lab implements Serializable {

    private int id = 1;
    private String name = "lab";
}

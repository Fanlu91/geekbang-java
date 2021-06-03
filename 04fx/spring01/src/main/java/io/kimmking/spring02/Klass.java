package io.kimmking.spring02;

import io.kimmking.spring01.Student;
import lombok.Data;

import java.util.List;

/**
 * 要在此类上做aop增强
 * 没有使用接口
 */
@Data
public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}

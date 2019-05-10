package com.spring.data.jpa.example.jpademo.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeVO {

    private String name;

    private long salary;
}

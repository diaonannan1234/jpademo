package com.spring.data.jpa.example.jpademo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {

    private String id;

    private String name;

    private Integer age;

}

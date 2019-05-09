package com.spring.data.jpa.example.jpademo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_role")
public class Contract  extends AbstractEntity{
    private String contractName;

    private String context;
}

package com.spring.data.jpa.example.jpademo.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "t_contract")
public class Contract  extends AbstractEntity{
    private String contractName;

    private String context;

    private BigDecimal bigDecimal;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String text;

}

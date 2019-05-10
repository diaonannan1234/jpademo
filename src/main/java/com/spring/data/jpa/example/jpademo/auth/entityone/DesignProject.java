package com.spring.data.jpa.example.jpademo.auth.entityone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DP")
public class DesignProject extends Project {
}

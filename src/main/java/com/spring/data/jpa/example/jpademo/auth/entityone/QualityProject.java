package com.spring.data.jpa.example.jpademo.auth.entityone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QP")
public class QualityProject extends Project {
}

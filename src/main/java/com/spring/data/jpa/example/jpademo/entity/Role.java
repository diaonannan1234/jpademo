package com.spring.data.jpa.example.jpademo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "t_role")
public class Role extends AbstractEntity{

    private String name;

    @OneToMany()
    private List<User> users;

    public void removeUser(User user){
        users.remove(user);
    }
}

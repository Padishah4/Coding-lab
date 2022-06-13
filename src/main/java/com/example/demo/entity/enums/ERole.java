package com.example.demo.entity.enums;

import com.example.demo.entity.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ERole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}

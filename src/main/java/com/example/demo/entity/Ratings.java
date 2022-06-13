package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String lastname;
    private String ratings;
    private String caption;
    private String location;
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

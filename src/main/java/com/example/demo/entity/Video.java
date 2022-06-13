package com.example.demo.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Video{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Lob
    private byte[] data;
    public Video(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long postId;
}

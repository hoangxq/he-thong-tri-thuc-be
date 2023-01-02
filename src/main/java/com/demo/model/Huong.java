package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "huong")
public class Huong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "huong")
    private String huong;
}

package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quan_huyen")
public class QuanHuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten")
    private String ten;
}

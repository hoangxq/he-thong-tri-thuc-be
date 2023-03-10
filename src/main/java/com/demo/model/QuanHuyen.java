package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_quan_huyen")
public class QuanHuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "mo_ta")
    private String moTa;
}

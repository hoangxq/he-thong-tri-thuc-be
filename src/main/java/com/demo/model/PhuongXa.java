package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_phuong_xa")
public class PhuongXa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ten")
    private String ten;
    @ManyToOne
    @JoinColumn(name = "id_tbl_quan_huyen")
    private QuanHuyen quanHuyen;
    @Column(name = "mo_ta")
    private String moTa;
}

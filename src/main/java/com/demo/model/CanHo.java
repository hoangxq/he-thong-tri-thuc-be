package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "can_ho")
public class CanHo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dien_tich")
    private Double dienTich;
    @Column(name = "gia")
    private Double giaTien;
    @Column(name = "huong")
    private String huong;
    @Column(name = "quan_huyen")
    private String quanHuyen;
    @Column(name = "so_phong_ngu")
    private int soPhongNgu;
    @Column(name = "so_phong_wc")
    private int soPhongWc;
    @Column(name = "trang_thai")
    private String trangThai;
}

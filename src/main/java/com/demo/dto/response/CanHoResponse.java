package com.demo.dto.response;

import lombok.Data;

@Data
public class CanHoResponse {
    private Long id;
    private Double dienTich;
    private Double giaTien;
    private String huong;
    private String quanHuyen;
    private int soPhongNgu;
    private int soPhongWc;
    private String trangThai;
}

package com.demo.dto.request;

import lombok.Data;

@Data
public class CanHoRequest {
    private Double dienTich;
    private Double giaTien;
    private String huong;
    private String quanHuyen;
    private int soPhongNgu;
    private int soPhongWc;
    private String trangThai;
}

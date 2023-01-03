package com.demo.dto.response;

import com.demo.model.Huong;
import lombok.Data;

@Data
public class CanHoRes {
    private Long id;
    private Double dienTich;
    private Double giaTien;
    private Huong huong;
    private PhuongXaResponse phuongXaRes;
    private int soPhongNgu;
    private int soPhongWc;
    private String trangThai;
}

package com.demo.dto.request;

import com.demo.dto.response.PhuongXaResponse;
import com.demo.model.Huong;
import lombok.Data;

@Data
public class CanHoReq {
    private Double dienTich;
    private String huong;
    private Long idPhuongXa;
    private int soPhongNgu;
    private int soPhongWc;
    private Double giaTien;
}

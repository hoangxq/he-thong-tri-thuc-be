package com.demo.dto.request;

import com.demo.dto.response.PhuongXaResponse;
import com.demo.model.Huong;
import lombok.Data;

@Data
public class CanHoReq {
    private Double dienTich;
    private Huong huong;
    private PhuongXaResponse phuongXaRes;
    private int soPhongNgu;
    private int soPhongWc;
}

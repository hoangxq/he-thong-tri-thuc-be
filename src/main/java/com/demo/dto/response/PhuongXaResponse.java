package com.demo.dto.response;

import lombok.Data;

@Data
public class PhuongXaResponse {
    private Long id;
    private String ten;
    private String moTa;
    private QuanHuyenResponse quanHuyen;
}

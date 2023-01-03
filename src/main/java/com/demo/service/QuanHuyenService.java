package com.demo.service;

import com.demo.dto.request.QuanHuyenRequest;
import com.demo.dto.response.QuanHuyenResponse;

import java.util.List;

public interface QuanHuyenService {
    List<QuanHuyenResponse> getAllQuanHuyen();
    QuanHuyenResponse createQuanHuyen(QuanHuyenRequest quanHuyenRequest);
}

package com.demo.service;

import com.demo.dto.request.PhuongXaRequest;
import com.demo.dto.response.PhuongXaResponse;

import java.util.List;

public interface PhuongXaService {
    List<PhuongXaResponse> getAllPhuongXa();
    List<PhuongXaResponse> getPhuongXaByQuanHuyen(Integer idQuanHuyen);
    PhuongXaResponse createPhuongXa(PhuongXaRequest phuongXaRequest);
}

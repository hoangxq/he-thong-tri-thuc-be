package com.demo.service;

import com.demo.dto.request.CanHoReq;
import com.demo.dto.response.CanHoRes;

import java.util.List;

public interface CanHoService {
    List<CanHoRes> getAllCanHoDaXuLy();
    List<CanHoRes> getAllCanHoDangXuLy();
    CanHoRes dinhGiaCanHo(CanHoReq canHoReq);
    CanHoRes themMoiCanHo(CanHoReq canHoReq);
    CanHoRes capNhatCanHo(Long idCanHo, CanHoReq canHoReq);
    CanHoRes getCanHoById(Long idCanHo);
    void xoaCanHo(Long idCanHo);
}

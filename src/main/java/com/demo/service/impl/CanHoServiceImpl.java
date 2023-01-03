package com.demo.service.impl;

import com.demo.dto.request.CanHoReq;
import com.demo.dto.response.CanHoRes;
import com.demo.dto.response.PhuongXaResponse;
import com.demo.model.TrangThai;
import com.demo.repository.CanHoRepository;
import com.demo.service.CanHoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CanHoServiceImpl implements CanHoService {
    private final CanHoRepository canHoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CanHoRes> getAllCanHoDaXuLy() {
        return canHoRepository.findAllByTrangThai(TrangThai.DA_XU_LY).stream()
                .map(e -> {
                    var res = modelMapper.map(e, CanHoRes.class);
                    res.setPhuongXaRes(modelMapper.map(e.getPhuongXa(), PhuongXaResponse.class));
                    return res;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CanHoRes> getAllCanHoDangXuLy() {
        return canHoRepository.findAllByTrangThai(TrangThai.DANG_XU_LY).stream()
                .map(e -> {
                    var res = modelMapper.map(e, CanHoRes.class);
                    res.setPhuongXaRes(modelMapper.map(e.getPhuongXa(), PhuongXaResponse.class));
                    return res;
                }).collect(Collectors.toList());
    }

    @Override
    public CanHoRes dinhGiaCanHo(CanHoReq canHoReq) {
        return null;
    }
}

package com.demo.service.impl;

import com.demo.dto.request.PhuongXaRequest;
import com.demo.dto.response.PhuongXaResponse;
import com.demo.dto.response.QuanHuyenResponse;
import com.demo.model.PhuongXa;
import com.demo.repository.PhuongXaRepository;
import com.demo.repository.QuanHuyenRepository;
import com.demo.service.PhuongXaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhuongXaServiceImpl implements PhuongXaService {
    private final PhuongXaRepository phuongXaRepository;
    private final QuanHuyenRepository quanHuyenRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PhuongXaResponse> getAllPhuongXa() {
        return phuongXaRepository.findAll().stream()
                .map(e -> {
                    var res = modelMapper.map(e, PhuongXaResponse.class);
                    res.setQuanHuyen(modelMapper.map(e.getQuanHuyen(), QuanHuyenResponse.class));
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PhuongXaResponse> getPhuongXaByQuanHuyen(Integer idQuanHuyen) {
        return phuongXaRepository.findAllByQuanHuyen_Id(idQuanHuyen).stream()
                .map(e -> {
                    var res = modelMapper.map(e, PhuongXaResponse.class);
                    res.setQuanHuyen(modelMapper.map(e.getQuanHuyen(), QuanHuyenResponse.class));
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PhuongXaResponse createPhuongXa(PhuongXaRequest phuongXaRequest) {
        var res = modelMapper.map(phuongXaRequest, PhuongXa.class);
        res.setQuanHuyen(quanHuyenRepository.findById(phuongXaRequest.getIdQuanHuyen()).get());
        return modelMapper.map(
                phuongXaRepository.save(res),
                PhuongXaResponse.class
        );
    }
}

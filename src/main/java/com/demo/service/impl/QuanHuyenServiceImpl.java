package com.demo.service.impl;

import com.demo.dto.request.QuanHuyenRequest;
import com.demo.dto.response.QuanHuyenResponse;
import com.demo.model.QuanHuyen;
import com.demo.repository.QuanHuyenRepository;
import com.demo.service.QuanHuyenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuanHuyenServiceImpl implements QuanHuyenService {
    private final QuanHuyenRepository quanHuyenRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<QuanHuyenResponse> getAllQuanHuyen() {
        return quanHuyenRepository.findAll().stream()
                .map(e -> modelMapper.map(e, QuanHuyenResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuanHuyenResponse createQuanHuyen(QuanHuyenRequest quanHuyenRequest) {
        return modelMapper.map(
                quanHuyenRepository.save(modelMapper.map(quanHuyenRequest, QuanHuyen.class)),
                QuanHuyenResponse.class
        );
    }
}

package com.demo.service.impl;

import com.demo.dto.request.CanHoRequest;
import com.demo.dto.response.CanHoResponse;
import com.demo.model.CanHo;
import com.demo.model.TrongSo;
import com.demo.repository.CanHoRepository;
import com.demo.service.CanHoService;
import com.demo.service.XuLyDoTuongDong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CanHoServiceImpl implements CanHoService {

    private final ModelMapper modelMapper;
    private final CanHoRepository canHoRepository;
    private final XuLyDoTuongDong xuLyDoTuongDong;

//    @Override
//    public Page<CanHoResponse> getAllCanHo(PagingReq pagingReq) {
//        var canHoPage = canHoRepository.findAll(pagingReq.makePageable());
//        return canHoPage.map(e -> modelMapper.map(e, CanHoResponse.class));
//    }

    @Override
    public List<CanHoResponse> getAllCanHo() {
        var listCanHo = canHoRepository.findAll();
//
//        for (int i = 0; i < listCanHo.size(); i++){
//            var canHo = listCanHo.get(i);
//            var res = canHoRepository.findByDienTichAndQuanHuyenAndSoPhongNguAndSoPhongWcAndHuong(
//                    canHo.getDienTich(), canHo.getQuanHuyen(), canHo.getSoPhongNgu(), canHo.getSoPhongWc(),
//                    canHo.getHuong()
//            );
//            if (res.size() > 1)
//                for (int j = 1; j < res.size(); j++)
//                    canHoRepository.delete(res.get(j));
//        }
        return listCanHo.stream().map(e -> modelMapper.map(e, CanHoResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Page<CanHoResponse> getCanHoByTrangThai(String trangThai) {
        return null;
    }

    @Override
    public CanHoResponse getCanHoById(Long id) {
        return null;
    }

    @Override
    public CanHoResponse editCanHo(Long id, CanHoRequest source) {
        return null;
    }

    @Override
    public CanHoResponse handleDataAndCreateCanHo(CanHoRequest canHoRequest) {
        var canHoResponse = canHoRepository.findByDienTichAndQuanHuyenAndSoPhongNguAndSoPhongWcAndHuong(
                canHoRequest.getDienTich(), canHoRequest.getQuanHuyen(), canHoRequest.getSoPhongNgu(),
                canHoRequest.getSoPhongWc(), canHoRequest.getHuong()
        ).map(e -> modelMapper.map(e, CanHoResponse.class));
        if (canHoResponse.isPresent())
            return canHoResponse.get();

        List<CanHo> listCanHo = canHoRepository.findAll();
        double giaTien = 0;
        for (int i = 0; i < listCanHo.size(); i++){
            CanHo canHoSource = listCanHo.get(i);
            double doTuongDongDienTich = xuLyDoTuongDong.dienTich(canHoRequest.getDienTich(), canHoSource.getDienTich());
            double doTuongDongQuanHuyen = xuLyDoTuongDong.quanHuyen(canHoRequest.getQuanHuyen(), canHoSource.getQuanHuyen());
            double doTuongDongSoPhongNgu = xuLyDoTuongDong.soPhongNgu(canHoRequest.getSoPhongNgu(), canHoSource.getSoPhongNgu());
            double doTuongDongSoPhongWc = xuLyDoTuongDong.soPhongWc(canHoRequest.getSoPhongWc(), canHoSource.getSoPhongWc());
            double doTuongDongHuong = xuLyDoTuongDong.huong(canHoRequest.getHuong(), canHoSource.getHuong());

            double doTuongDongTrungBinh = (doTuongDongDienTich * TrongSo.DIEN_TICH +
                    doTuongDongQuanHuyen * TrongSo.QUAN_HUYEN +
                    doTuongDongSoPhongNgu * TrongSo.SO_PHONG_NGU +
                    doTuongDongSoPhongWc * TrongSo.SO_PHONG_WC +
                    doTuongDongHuong * TrongSo.HUONG) / TrongSo.TONG_TRONG_SO;

            giaTien += (canHoSource.getGiaTien() * doTuongDongTrungBinh)/listCanHo.size();
//            log.error(canHoSource.getId()+";"+doTuongDongTrungBinh+";"+giaTien);
//            log.error(canHoSource.getId()+";"+doTuongDongDienTich+";"+doTuongDongQuanHuyen+";"+doTuongDongSoPhongNgu+";"+doTuongDongSoPhongWc+";"+doTuongDongHuong);
        }
        canHoRequest.setGiaTien(giaTien);
        canHoRequest.setTrangThai("PENDING");

//        var canHoHandle = canHoRepository.save(modelMapper.map(canHoRequest, CanHo.class));
        var canHoHandle = modelMapper.map(canHoRequest, CanHo.class);
        return modelMapper.map(canHoHandle, CanHoResponse.class);

    }
}

package com.demo.service.impl;

import com.demo.dto.request.CanHoReq;
import com.demo.dto.response.CanHoRes;
import com.demo.dto.response.PhuongXaResponse;
import com.demo.model.CanHo;
import com.demo.model.PhuongXa;
import com.demo.model.TrangThai;
import com.demo.model.TrongSo;
import com.demo.repository.CanHoRepository;
import com.demo.repository.PhuongXaRepository;
import com.demo.service.CanHoService;
import com.demo.service.XuLyDoTuongDong;
import com.demo.service.utils.MappingHelper;
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
    private final MappingHelper mappingHelper;
    private final XuLyDoTuongDong xuLyDoTuongDong;
    private final PhuongXaRepository phuongXaRepository;

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
        var canHoResponse = canHoRepository.findByDienTichAndAndPhuongXa_IdAndSoPhongNguAndSoPhongWcAndHuong(
                canHoReq.getDienTich(), canHoReq.getIdPhuongXa(), canHoReq.getSoPhongNgu(),
                canHoReq.getSoPhongWc(), canHoReq.getHuong()
        ).map(e -> {
            var res = modelMapper.map(e, CanHoRes.class);
            res.setPhuongXaRes(modelMapper.map(e.getPhuongXa(), PhuongXaResponse.class));
            return res;
        });
        if (canHoResponse.isPresent())
            return canHoResponse.get();

        PhuongXa phuongXa = phuongXaRepository.findById(canHoReq.getIdPhuongXa()).get();

        var canHo = modelMapper.map(canHoReq, CanHo.class);
        canHo.setPhuongXa(phuongXa);

        List<CanHo> listCanHo = canHoRepository.findAllByTrangThai(TrangThai.DA_XU_LY);
        long giaTien = 0;
        int count = 0;
        for (int i = 0; i < listCanHo.size(); i++){
            CanHo canHoSource = listCanHo.get(i);
            double doTuongDongDienTich = xuLyDoTuongDong.dienTich(canHoReq.getDienTich(), canHoSource.getDienTich());
            double doTuongDongQuanHuyen = xuLyDoTuongDong.quanHuyen(phuongXa.getQuanHuyen().getTen(), canHoSource.getPhuongXa().getQuanHuyen().getTen());
            double doTuongDongPhuongXa = xuLyDoTuongDong.phuongXa(phuongXa.getTen(), canHoSource.getPhuongXa().getTen());

            double doTuongDongSoPhongNgu = xuLyDoTuongDong.soPhongNgu(canHoReq.getSoPhongNgu(), canHoSource.getSoPhongNgu());
            double doTuongDongSoPhongWc = xuLyDoTuongDong.soPhongWc(canHoReq.getSoPhongWc(), canHoSource.getSoPhongWc());
            double doTuongDongHuong = xuLyDoTuongDong.huong(canHoReq.getHuong(), canHoSource.getHuong());

            double doTuongDongTrungBinh = (doTuongDongDienTich * TrongSo.DIEN_TICH +
                    doTuongDongQuanHuyen * TrongSo.QUAN_HUYEN +
                    doTuongDongPhuongXa * TrongSo.PHUONG_XA +
                    doTuongDongSoPhongNgu * TrongSo.SO_PHONG_NGU +
                    doTuongDongSoPhongWc * TrongSo.SO_PHONG_WC +
                    doTuongDongHuong * TrongSo.HUONG) / TrongSo.TONG_TRONG_SO;

//            log.error(canHoSource.getId()+";"+doTuongDongTrungBinh+";"+giaTien);
//            log.error(canHoSource.getId()+";"+doTuongDongDienTich+";"+doTuongDongQuanHuyen+";"+doTuongDongSoPhongNgu+";"+doTuongDongSoPhongWc+";"+doTuongDongHuong);

            if (doTuongDongTrungBinh >= 0.8){
                giaTien += canHoSource.getGiaTien() * doTuongDongTrungBinh;
                count++;
            }
        }
        if(count != 0)
            giaTien = giaTien/count;
        canHo.setGiaTien(Double.valueOf(giaTien));
        canHo.setTrangThai(TrangThai.DANG_XU_LY);
//        log.error(canHo.getGiaTien()+"");

        var canHoRes = modelMapper.map(canHoRepository.save(canHo), CanHoRes.class);
        canHoRes.setPhuongXaRes(modelMapper.map(canHo.getPhuongXa(), PhuongXaResponse.class));
        return canHoRes;
    }

    @Override
    public CanHoRes themMoiCanHo(CanHoReq canHoReq) {
        var canHoResponse = canHoRepository.findByDienTichAndAndPhuongXa_IdAndSoPhongNguAndSoPhongWcAndHuong(
                canHoReq.getDienTich(), canHoReq.getIdPhuongXa(), canHoReq.getSoPhongNgu(),
                canHoReq.getSoPhongWc(), canHoReq.getHuong()
        ).map(e -> modelMapper.map(e, CanHoRes.class));
        if (canHoResponse.isPresent())
            return canHoResponse.get();

        PhuongXa phuongXa = phuongXaRepository.findById(canHoReq.getIdPhuongXa()).get();

        var canHo = modelMapper.map(canHoReq, CanHo.class);
        canHo.setPhuongXa(phuongXa);

        canHo.setTrangThai(TrangThai.DA_XU_LY);

        return modelMapper.map(canHoRepository.save(canHo), CanHoRes.class);
    }

    @Override
    public CanHoRes capNhatCanHo(Long idCanHo, CanHoReq canHoReq) {
        var canHo = canHoRepository.findById(idCanHo).get();
        mappingHelper.mapIfSourceNotNullAndStringNotBlank(canHoReq, canHo);
        canHo.setPhuongXa(phuongXaRepository.findById(canHoReq.getIdPhuongXa()).get());
        var canHoRes = modelMapper.map(canHoRepository.save(canHo), CanHoRes.class);
        canHoRes.setPhuongXaRes(modelMapper.map(canHo.getPhuongXa(), PhuongXaResponse.class));
        return canHoRes;
    }

    @Override
    public CanHoRes getCanHoById(Long idCanHo) {
        return canHoRepository.findById(idCanHo)
                .map(e -> {
                        var res = modelMapper.map(e, CanHoRes.class);
                        res.setPhuongXaRes(modelMapper.map(e.getPhuongXa(), PhuongXaResponse.class));
                        return res;
                }).get();
    }

}

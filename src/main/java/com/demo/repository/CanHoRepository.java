package com.demo.repository;

import com.demo.model.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanHoRepository extends JpaRepository<CanHo, Long> {
    List<CanHo> findAllByTrangThai(String trangThai);
    Optional<CanHo> findByDienTichAndAndPhuongXa_IdAndSoPhongNguAndSoPhongWcAndHuong(Double dienTich, Long idPhuongXa, int soPhongNgu,
                                                                                     int soPhongWc, String huong);
}

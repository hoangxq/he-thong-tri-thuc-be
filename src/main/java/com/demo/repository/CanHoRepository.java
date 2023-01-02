package com.demo.repository;

import com.demo.model.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanHoRepository extends JpaRepository<CanHo, Long> {
    Optional<CanHo> findByDienTichAndQuanHuyenAndSoPhongNguAndSoPhongWcAndHuong(
            Double dienTich, String quanHuyen, int soPhongNgu, int soPhongWc, String huong
    );
}

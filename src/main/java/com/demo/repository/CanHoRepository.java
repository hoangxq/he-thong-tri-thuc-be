package com.demo.repository;

import com.demo.model.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanHoRepository extends JpaRepository<CanHo, Long> {
    List<CanHo> findAllByTrangThai(String trangThai);

    Optional<CanHo> findByDienTichAndPhuongXa_IdAndSoPhongNguAndSoPhongWcAndHuong(Double dienTich, Long idPhuongXa, int soPhongNgu,
                                                                                  int soPhongWc, String huong);

    List<CanHo> findByDienTichAndPhuongXa_IdAndSoPhongNguAndHuongAndSoPhongWc(Double dienTich, Long idPhuongXa, int soPhongNgu, String huong,
                                                                              int soPhongWc);

    void deleteById(Long id);
}

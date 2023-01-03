package com.demo.repository;

import com.demo.model.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanHoRepository extends JpaRepository<CanHo, Long> {
    List<CanHo> findAllByTrangThai(String trangThai);
}

package com.demo.repository;

import com.demo.model.PhuongXa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhuongXaRepository extends JpaRepository<PhuongXa, Long> {
    List<PhuongXa> findAllByQuanHuyen_Id(Integer idQuanHuyen);
}

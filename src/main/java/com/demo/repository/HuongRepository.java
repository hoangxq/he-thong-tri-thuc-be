package com.demo.repository;

import com.demo.model.Huong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuongRepository extends JpaRepository<Huong, Integer> {
}

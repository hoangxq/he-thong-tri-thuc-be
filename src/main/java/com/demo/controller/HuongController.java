package com.demo.controller;

import com.demo.repository.HuongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/huong")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class HuongController {
    private final HuongRepository huongRepository;

    @GetMapping
    public ResponseEntity<?> getAllHuong(){
        return ResponseEntity.ok(huongRepository.findAll());
    }
}

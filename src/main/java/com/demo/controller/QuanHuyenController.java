package com.demo.controller;

import com.demo.dto.request.QuanHuyenRequest;
import com.demo.service.QuanHuyenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quan-huyen")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QuanHuyenController {
    private final QuanHuyenService quanHuyenService;

    @GetMapping
    public ResponseEntity<?> getAllQuanHuyen(){
        return ResponseEntity.ok(quanHuyenService.getAllQuanHuyen());
    }

    @PostMapping
    public ResponseEntity<?> createQuanHuyen(@RequestBody QuanHuyenRequest quanHuyenRequest){
        return ResponseEntity.ok(quanHuyenService.createQuanHuyen(quanHuyenRequest));
    }
}

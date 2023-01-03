package com.demo.controller;

import com.demo.dto.request.PhuongXaRequest;
import com.demo.service.PhuongXaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phuong-xa")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PhuongXaController {
    private final PhuongXaService phuongXaService;

    @GetMapping
    public ResponseEntity<?> getAllPhuongXa(){
        return ResponseEntity.ok(phuongXaService.getAllPhuongXa());
    }

    @PostMapping
    public ResponseEntity<?> createPhuongXa(@RequestBody PhuongXaRequest phuongXaRequest){
        return ResponseEntity.ok(phuongXaService.createPhuongXa(phuongXaRequest));
    }
}
package com.demo.controller;

import com.demo.dto.request.CanHoRequest;
import com.demo.service.CanHoService;
import com.demo.service.components.PagingReq;
import com.demo.service.components.PagingRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/can-ho")
public class CanHoController {
    private final CanHoService canHoService;
    private final ModelMapper modelMapper;

//    @GetMapping
//    public ResponseEntity<?> getAllCanHo (@Validated PagingReq pagingReq){
//        return ResponseEntity.ok(PagingRes.of(canHoService.getAllCanHo(pagingReq)));
//    }

    @GetMapping
    public ResponseEntity<?> getAllCanHo (){
        return ResponseEntity.ok(canHoService.getAllCanHo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCanHoById (@PathVariable Long id){
        return null;
    }

    @GetMapping("/{trangThai}")
    public ResponseEntity<?> getCanHoByTrangThai (@PathVariable String trangThai){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCanHo (@PathVariable Long id, @RequestBody CanHoRequest canHoRequest){
        return null;
    }

    @PostMapping
    public ResponseEntity<?> handleDataAndCreateCanHo (@RequestBody CanHoRequest canHoRequest){
        return ResponseEntity.ok(canHoService.handleDataAndCreateCanHo(canHoRequest));
    }
}

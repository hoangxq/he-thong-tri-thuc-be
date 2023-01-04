package com.demo.controller;

import com.demo.dto.request.CanHoReq;
import com.demo.service.CanHoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/can-ho")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class CanHoController {
    private final CanHoService canHoService;

    @GetMapping("/da-xu-ly")
    public ResponseEntity<?> getAllCanHoDaXuLy(){
        return ResponseEntity.ok(canHoService.getAllCanHoDaXuLy());
    }

    @GetMapping("/dang-xu-ly")
    public ResponseEntity<?> getAllCanHoDangXuLy(){
        return ResponseEntity.ok(canHoService.getAllCanHoDangXuLy());
    }

    @GetMapping("/{idCanHo}")
    public ResponseEntity<?> getCanHoById(@PathVariable Long idCanHo){
        return ResponseEntity.ok(canHoService.getCanHoById(idCanHo));
    }

    @PostMapping("/dinh-gia")
    public ResponseEntity<?> dinhGiaCanHo(@RequestBody CanHoReq canHoReq){
        return ResponseEntity.ok(canHoService.dinhGiaCanHo(canHoReq));
    }

    @PostMapping("/them-moi")
    public ResponseEntity<?> themMoiCanHo(@RequestBody CanHoReq canHoReq){
        return ResponseEntity.ok(canHoService.themMoiCanHo(canHoReq));
    }

    @PutMapping("/{idCanHo}")
    public ResponseEntity<?> capNhatCanHo(@PathVariable Long idCanHo, @RequestBody CanHoReq canHoReq){
        return ResponseEntity.ok(canHoService.capNhatCanHo(idCanHo, canHoReq));
    }
}

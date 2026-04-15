package com.desafio.Cep_Santander.controller;

import com.desafio.Cep_Santander.dto.CepResponseDTO;
import com.desafio.Cep_Santander.entity.CepLog;
import com.desafio.Cep_Santander.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarCep(cep));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CepResponseDTO> adicionarCep(@RequestBody CepResponseDTO request){
        return ResponseEntity.ok(service.adicionarCep(request));
    }

    @GetMapping("/logs")
    public ResponseEntity<List<CepLog>> logs() {
        return ResponseEntity.ok(service.listarLogs());
    }
}

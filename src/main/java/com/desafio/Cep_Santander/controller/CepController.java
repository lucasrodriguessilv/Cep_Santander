package com.desafio.Cep_Santander.controller;

import com.desafio.Cep_Santander.dto.CepResponseDTO;
import com.desafio.Cep_Santander.entity.CepLog;
import com.desafio.Cep_Santander.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/logs")
    public ResponseEntity<List<CepLog>> logs() {
        return ResponseEntity.ok(service.listarLogs());
    }
}

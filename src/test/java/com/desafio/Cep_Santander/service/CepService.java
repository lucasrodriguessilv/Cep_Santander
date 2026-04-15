package com.desafio.Cep_Santander.service;

import com.desafio.Cep_Santander.client.CepClient;
import com.desafio.Cep_Santander.dto.CepResponseDTO;
import com.desafio.Cep_Santander.entity.CepLog;
import com.desafio.Cep_Santander.repository.CepLogRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Service
public class CepService {

    private final CepClient cepClient;
    private final CepLogRepository repository;

    public CepService(CepClient cepClient, CepLogRepository repository) {
        this.cepClient = cepClient;
        this.repository = repository;
    }

    public CepResponseDTO buscarCep(String cep) {
        CepResponseDTO response = cepClient.buscarCep(cep);

        CepLog log = new CepLog();
        log.setCep(response.cep);
        log.setLogadradouro(response.logradouro);
        log.setBairro(response.bairro);
        log.setCidade(response.localidade);
        log.setEstado(response.uf);
       // log.LocalDateTime(LocalDateTime.now());

        repository.save(log);

        return response;
    }

    public List<CepLog> listarLogs() {
        return repository.findAll();
    }
}
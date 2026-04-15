package com.desafio.Cep_Santander.service;

import com.desafio.Cep_Santander.client.CepClient;
import com.desafio.Cep_Santander.dto.CepResponseDTO;
import com.desafio.Cep_Santander.entity.CepLog;
import com.desafio.Cep_Santander.repository.CepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CepService {

    private final CepClient cepClient;
    private final CepLogRepository repository;

    public CepResponseDTO buscarCep(String cep) {

        CepResponseDTO response = cepClient.buscarCep(cep);

        salvarLog(response);

        return response;
    }

    public List<CepLog> listarLogs() {
        return repository.findAll();
    }

    public CepResponseDTO adicionarCep(CepResponseDTO request) {

        salvarLog(request);

        return request;
    }

    private void salvarLog(CepResponseDTO response) {

        CepLog log = new CepLog();

        log.setCep(response.getCep());
        log.setLogradouro(response.getLogradouro());
        log.setBairro(response.getBairro());
        log.setCidade(response.getLocalidade());
        log.setEstado(response.getUf());
        log.setDataConsulta(LocalDateTime.now());

        repository.save(log);
    }
}
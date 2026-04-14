package com.desafio.Cep_Santander.client;

import com.desafio.Cep_Santander.dto.CepResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CepClient {
    private final RestTemplate restTemplate = new RestTemplate();
    public CepResponseDTO buscarCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, CepResponseDTO.class);
    }
}

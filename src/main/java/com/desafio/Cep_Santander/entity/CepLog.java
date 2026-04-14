package com.desafio.Cep_Santander.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cep_logs")
    public class CepLog {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
        private String cep;
        private String logadradouro;
        private String bairro;
        private String cidade;
        private String estado;
        private LocalDateTime localConsulta;

}

package com.pruebatecnica.marvel_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.marvel_api.model.consultaVO;
import com.pruebatecnica.marvel_api.repository.ConsultaRepository;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
     @Autowired
    private ConsultaRepository consultaRepository;

    @GetMapping
    public List<consultaVO> getAllConsultas() {
        return consultaRepository.findAll();
    }
    
}

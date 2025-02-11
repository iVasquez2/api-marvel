/*
 * Define un Endpoint; Hace el llamado al servicio
 */

package com.pruebatecnica.marvel_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.marvel_api.dto.marvelResponse;
import com.pruebatecnica.marvel_api.service.marvelConsultarService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/marvel")
public class marvelController {

    @Autowired
    private marvelConsultarService marvelconsultarservice;

    @GetMapping("/characters")
    public marvelResponse getCharacters() {
        return marvelconsultarservice.getCharacters();
    }
    
    
}

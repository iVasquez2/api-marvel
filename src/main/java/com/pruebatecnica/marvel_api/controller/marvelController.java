/*
 * Define un Endpoint; Hace el llamado al servicio
 */

package com.pruebatecnica.marvel_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.marvel_api.dto.marvelResponse;
import com.pruebatecnica.marvel_api.service.marvelConsultarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/marvel")
public class marvelController {

    @Autowired
    private marvelConsultarService marvelconsultarservice;

    @GetMapping("/characters")
    public marvelResponse getCharacters(@RequestParam(defaultValue = "21")int limit, 
    @RequestParam(defaultValue = "0")int offset) {
        return marvelconsultarservice.getCharacters(limit, offset);
    }

    @GetMapping("/characters/{characterId}/series")
    public marvelResponse getCharacterSeries(@PathVariable String characterId) {
        return marvelconsultarservice.getCharacterSeries(characterId);
    }
    
    
}

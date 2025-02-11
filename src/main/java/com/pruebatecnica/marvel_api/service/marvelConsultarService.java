/*
 * Realiza un llamado al Feign Client para la obtencion de una lista de personajes de Marvel
 */
package com.pruebatecnica.marvel_api.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.marvel_api.client.marvelClient;
import com.pruebatecnica.marvel_api.dto.marvelResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class marvelConsultarService {

    @Autowired
    private marvelClient marvelclient;
    @Autowired
    private marvelAutentificacionService marvelautentificacionservice;

    public marvelResponse getCharacters(){
        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        String hash = marvelautentificacionservice.generarHash(timestamp);
        String publicKey = marvelautentificacionservice.getPublicKey();

        String url = "https://gateway.marvel.com/v1/public/characters?ts=" + timestamp + 
        "&apikey=" + publicKey + "&hash=" + hash;
System.out.println("URL generada: " + url);

        return marvelclient.getCharacters(timestamp, publicKey, hash);
    }
}

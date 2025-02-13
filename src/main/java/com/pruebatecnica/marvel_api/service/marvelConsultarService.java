/*
 * Realiza un llamado al Feign Client para la obtencion de una lista de personajes de Marvel
 */
package com.pruebatecnica.marvel_api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.marvel_api.client.marvelClient;
import com.pruebatecnica.marvel_api.dto.marvelResponse;
import com.pruebatecnica.marvel_api.model.consultaVO;
import com.pruebatecnica.marvel_api.repository.ConsultaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class marvelConsultarService {

    @Autowired
    private marvelClient marvelclient;
    @Autowired
    private marvelAutentificacionService marvelautentificacionservice;
    @Autowired
    private ConsultaRepository consultaRepository;

    public marvelResponse getCharacters(){
        marvelResponse response = null;
        try {
            String timestamp = String.valueOf(Instant.now().getEpochSecond());
            String hash = marvelautentificacionservice.generarHash(timestamp);
            String publicKey = marvelautentificacionservice.getPublicKey();
            String url = "https://gateway.marvel.com/v1/public/characters?ts=" + timestamp + 
            "&apikey=" + publicKey + "&hash=" + hash;
            System.out.println("URL generada: " + url);
            //Consulta API
            response = marvelclient.getCharacters(timestamp, publicKey, hash);
            if (response == null){
                System.out.println("No se recibio respuesta");
            } else {
                System.out.println("Respuesta: " + response);
            }
        } catch (Exception e) {
            System.out.println("Error en la llamada" + e);
            e.printStackTrace();
        }

        try {
            if(response !=null){
        //Guardado en H2
        LocalDateTime fechaConsulta = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        consultaVO consulta = new consultaVO("/characters", fechaConsulta);
        consultaRepository.save(consulta);
        System.out.println("Guardado con exito!");
            }
        } catch (Exception e) {
            System.out.println("Error en la llamada" + e);
            e.printStackTrace();
        }

        return response;
    }
    
    public marvelResponse getCharacterSeries(String characterId) {
        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        String hash = marvelautentificacionservice.generarHash(timestamp);
        String publicKey = marvelautentificacionservice.getPublicKey();

        return marvelclient.getCharacterSeries(characterId, timestamp, publicKey, hash);
    }

}

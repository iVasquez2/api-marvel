/*
 * Definicion del cliente para la conexion, usa la URL definida en application.yml
 */

package com.pruebatecnica.marvel_api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruebatecnica.marvel_api.dto.marvelResponse;

@FeignClient(name = "marvelclient", url = "${marvel.api.base-url}/characters")
public interface marvelClient {

    @GetMapping
   marvelResponse getCharacters(
    @RequestParam("ts")
    String timestamp,
    @RequestParam("apikey")
    String apiKey,
    @RequestParam("hash")
    String hash
   );

   @GetMapping("/characters/{characterId}/series")
    marvelResponse getCharacterSeries(
        @RequestParam("characterId") String characterId,
        @RequestParam("ts") String timestamp,
        @RequestParam("apikey") String apiKey,
        @RequestParam("hash") String hash
    );

}

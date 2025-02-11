/* 
 * Mapea la respuesta de la API Marvel  a un modelo Java definido
*/

package com.pruebatecnica.marvel_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class marvelResponse {
    private marvelData data;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class marvelData {
    private marvelCharacter[] results;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class marvelCharacter{
    private String name;
    private String description;
}

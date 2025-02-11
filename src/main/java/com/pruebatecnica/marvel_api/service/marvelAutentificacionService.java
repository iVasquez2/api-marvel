/*
 * Clase que permitira la generacion de un HASH MD5 para la autentificacion de las llaves
 */

package com.pruebatecnica.marvel_api.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class marvelAutentificacionService {

    @Value("${marvel.api.public-key}")
    private String publicKey;

    @Value("${marvel.api.private-key}")
    private String privateKey;

    public String generarHash(String timestamp){
        try {
            String value = timestamp + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte [] hash = md.digest(value.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error de generacion de hash", e);
        }

    }

    public String getPublicKey(){
        return publicKey;
    }
}

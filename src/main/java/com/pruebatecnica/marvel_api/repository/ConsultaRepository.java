package com.pruebatecnica.marvel_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.marvel_api.model.consultaVO;

@Repository
public interface ConsultaRepository extends JpaRepository <consultaVO, Long> {

}

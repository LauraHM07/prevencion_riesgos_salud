package com.prevencion.prevencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevencion.prevencion.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    
}

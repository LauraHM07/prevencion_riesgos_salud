package com.prevencion.prevencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevencion.prevencion.model.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    
}

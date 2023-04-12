package com.prevencion.prevencion.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prevencion.prevencion.model.Trabajador;

public interface TrabajadorService {
    
    public Page<Trabajador> findAll(Pageable pageable);

    public Trabajador findById(int codigo);

    public void insert(Trabajador trabajador);

    public void update(Trabajador trabajador);

    public void delete(int codigo);
}

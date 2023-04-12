package com.prevencion.prevencion.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prevencion.prevencion.model.Empresa;

public interface EmpresaService {
    
    public Page<Empresa> findAll(Pageable pageable);

    public Empresa findById(int codigo);

    public void insert(Empresa empresa);

    public void update(Empresa empresa);

    public void delete(int codigo);
}

package com.prevencion.prevencion.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prevencion.prevencion.model.Empresa;
import com.prevencion.prevencion.repository.EmpresaRepository;
import com.prevencion.prevencion.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    
    @Autowired
    EmpresaRepository repository;

    @Override
    public Page<Empresa> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Empresa findById(int codigo) {
        Optional<Empresa> findById = repository.findById(codigo);

        if(findById != null) {
            return findById.get();
        }

        return null;
    }

    @Override
    public void insert(Empresa empresa){
        repository.save(empresa);
    }

    @Override
    public void update(Empresa empresa) {
        repository.save(empresa);
    }

    @Override
    public void delete(int codigo){
        repository.deleteById(codigo);
    }
}

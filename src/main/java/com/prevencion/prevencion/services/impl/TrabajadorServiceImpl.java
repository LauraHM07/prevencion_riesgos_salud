package com.prevencion.prevencion.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prevencion.prevencion.model.Trabajador;
import com.prevencion.prevencion.repository.TrabajadorRepository;
import com.prevencion.prevencion.services.TrabajadorService;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {
    
    @Autowired
    TrabajadorRepository repository;

    @Override
    public Page<Trabajador> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<Trabajador> findAll() {
        return repository.findAll();
    }


    @Override
    public Trabajador findById(int codigo) {
        Optional<Trabajador> findById = repository.findById(codigo);

        if(findById != null) {
            return findById.get();
        }

        return null;
    }

    @Override
    public void insert(Trabajador trabajador){
        repository.save(trabajador);
    }

    @Override
    public void update(Trabajador trabajador) {
        repository.save(trabajador);
    }

    @Override
    public void delete(int codigo){
        repository.deleteById(codigo);
    }
}

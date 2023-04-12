package com.prevencion.prevencion.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prevencion.prevencion.model.Revision;
import com.prevencion.prevencion.repository.RevisionRepository;
import com.prevencion.prevencion.services.RevisionService;

@Service
public class RevisionServiceImpl implements RevisionService {
    
    @Autowired
    RevisionRepository repository;

    @Override
    public Page<Revision> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Revision findById(int codigo) {
        Optional<Revision> findById = repository.findById(codigo);

        if(findById != null) {
            return findById.get();
        }

        return null;
    }

    @Override
    public void insert(Revision revision){
        repository.save(revision);
    }

    @Override
    public void update(Revision revision) {
        repository.save(revision);
    }

    @Override
    public void delete(int codigo){
        repository.deleteById(codigo);
    }
}

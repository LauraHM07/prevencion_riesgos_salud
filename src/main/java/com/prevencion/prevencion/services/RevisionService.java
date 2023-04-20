package com.prevencion.prevencion.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prevencion.prevencion.model.Revision;

public interface RevisionService {

    public Page<Revision> findAll(Pageable pageable);

    public Revision findById(int codigo);

    public void save(Revision revision);

    public void delete(int codigo);
}

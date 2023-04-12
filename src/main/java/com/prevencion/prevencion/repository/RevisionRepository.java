package com.prevencion.prevencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevencion.prevencion.model.Revision;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {
    
}

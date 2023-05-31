package com.prevencion.prevencion.services;

import java.util.List;

import com.prevencion.prevencion.model.Permiso;

public interface PermisoService {
    
    public List<Permiso> findAll();

    public Permiso findByID(int codigo);

    public Permiso insert(Permiso permiso);

    public void update(Permiso permiso);

    public void delete(int codigo);
}

package com.prevencion.prevencion.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prevencion.prevencion.model.Doctor;

public interface DoctorService {

    public Page<Doctor> findAll(Pageable pageable);
    public List<Doctor> findAll();

    public Doctor findById(int codigo);

    public void insert(Doctor doctor);

    public void update(Doctor doctor);

    public void delete(int codigo);
}

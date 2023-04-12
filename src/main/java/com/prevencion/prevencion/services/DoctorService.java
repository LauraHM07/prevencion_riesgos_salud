package com.prevencion.prevencion.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prevencion.prevencion.model.Doctor;

public interface DoctorService {

    public Page<Doctor> findAll(Pageable pageable);

    public Doctor findById(int codigo);

    public void insert(Doctor doctor);

    public void update(Doctor doctor);

    public void delete(int codigo);
}

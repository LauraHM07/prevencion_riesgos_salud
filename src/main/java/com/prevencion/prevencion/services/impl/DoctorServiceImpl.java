package com.prevencion.prevencion.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prevencion.prevencion.model.Doctor;
import com.prevencion.prevencion.repository.DoctorRepository;
import com.prevencion.prevencion.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
    
    @Autowired
    DoctorRepository repository;

    @Override
    public Page<Doctor> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    @Override
    public Doctor findById(int codigo) {
        Optional<Doctor> findById = repository.findById(codigo);

        if(findById != null) {
            return findById.get();
        }

        return null;
    }

    @Override
    public void insert(Doctor doctor){
        repository.save(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        repository.save(doctor);
    }

    @Override
    public void delete(int codigo){
        repository.deleteById(codigo);
    }
}

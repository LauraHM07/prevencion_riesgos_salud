package com.prevencion.prevencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prevencion.prevencion.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
    
}

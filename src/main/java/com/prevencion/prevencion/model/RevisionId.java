package com.prevencion.prevencion.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class RevisionId implements Serializable {
    
    private int doctor_codigo;
    private int trabajador_codigo;

    public RevisionId() {
    }

    public RevisionId(int doctor_codigo, int trabajador_codigo) {
        this.doctor_codigo = doctor_codigo;
        this.trabajador_codigo = trabajador_codigo;
    }

    public int getDoctor_codigo() {
        return doctor_codigo;
    }

    public void setDoctor_codigo(int doctor_codigo) {
        this.doctor_codigo = doctor_codigo;
    }

    public int getTrabajador_codigo() {
        return trabajador_codigo;
    }

    public void setTrabajador_codigo(int trabajador_codigo) {
        this.trabajador_codigo = trabajador_codigo;
    }

    
}

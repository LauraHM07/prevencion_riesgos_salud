package com.prevencion.prevencion.model;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Revision {

    @EmbeddedId
    private RevisionId id = new RevisionId();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @MapsId("doctor_codigo")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @MapsId("trabajador_codigo")
    private Trabajador trabajador;
    
    private String fecha;
    private List<String> pruebas;
    private String descripcion;
    
    public Revision() {
    }

    public RevisionId getId() {
        return id;
    }

    public void setId(RevisionId id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<String> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<String> pruebas) {
        this.pruebas = pruebas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

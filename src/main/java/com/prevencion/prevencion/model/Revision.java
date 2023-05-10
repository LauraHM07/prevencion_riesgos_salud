package com.prevencion.prevencion.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Revision {

    // @EmbeddedId
    // private RevisionId id = new RevisionId();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_codigo")
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name =  "trabajador_codigo")
    private Trabajador trabajador;
    
    private String fecha;
    private String descripcion;

    private Boolean tension;
    private Boolean pesoAltura;
    private Boolean audiometria;
    private Boolean controlVision;
    private Boolean electrocardiograma;
    private Boolean valoracionNeurologica;
    private Boolean analisisSangreOrina;
    
    public Revision() {
    }

    // public RevisionId getId() {
    //     return id;
    // }

    // public void setId(RevisionId id) {
    //     this.id = id;
    // }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTension() {
        return tension;
    }

    public void setTension(Boolean tension) {
        this.tension = tension;
    }

    public Boolean getPesoAltura() {
        return pesoAltura;
    }

    public void setPesoAltura(Boolean pesoAltura) {
        this.pesoAltura = pesoAltura;
    }

    public Boolean getAudiometria() {
        return audiometria;
    }

    public void setAudiometria(Boolean audiometria) {
        this.audiometria = audiometria;
    }

    public Boolean getControlVision() {
        return controlVision;
    }

    public void setControlVision(Boolean controlVision) {
        this.controlVision = controlVision;
    }

    public Boolean getElectrocardiograma() {
        return electrocardiograma;
    }

    public void setElectrocardiograma(Boolean electrocardiograma) {
        this.electrocardiograma = electrocardiograma;
    }

    public Boolean getValoracionNeurologica() {
        return valoracionNeurologica;
    }

    public void setValoracionNeurologica(Boolean valoracionNeurologica) {
        this.valoracionNeurologica = valoracionNeurologica;
    }

    public Boolean getAnalisisSangreOrina() {
        return analisisSangreOrina;
    }

    public void setAnalisisSangreOrina(Boolean analisisSangreOrina) {
        this.analisisSangreOrina = analisisSangreOrina;
    }
}

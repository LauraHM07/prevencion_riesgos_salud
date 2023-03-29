package com.prevencion.prevencion.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Revision {
    
    @Id
    @GeneratedValue
    private int codigo;

    private String fecha;
    private List<String> pruebas;
    private String descripcion;
    
    public Revision() {
    }

    public Revision(int codigo) {
        this.codigo = codigo;
    }

    public Revision(int codigo, String fecha, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Revision other = (Revision) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
}

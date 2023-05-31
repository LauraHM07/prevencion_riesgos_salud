package com.prevencion.prevencion.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prevencion.prevencion.model.Permiso;
import com.prevencion.prevencion.services.PermisoService;

@Service
public class PermisoServiceImpl implements PermisoService {
    
    @Value("${url.seguridad.rest.service}")
    String urlSeguridad;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Permiso> findAll() {
        Permiso[] per = restTemplate.getForObject(urlSeguridad + "permisos", Permiso[].class);
        List<Permiso> permisos = Arrays.asList(per);

        return permisos;
    }

    @Override
    public Permiso findByID(int codigo) {
        Permiso per = restTemplate.getForObject(urlSeguridad + "permisos/" + codigo, Permiso.class);

        return per;
    }

    @Override
    public Permiso insert(Permiso permiso) {
        Permiso per = restTemplate.postForObject(urlSeguridad + "permisos", permiso, Permiso.class);

        return per;
    }

    @Override
    public void update(Permiso permiso) {
        restTemplate.put(urlSeguridad + "permisos/" + permiso.getCodigo(), permiso);
    }

    @Override
    public void delete(int codigo) {
        restTemplate.delete(urlSeguridad + "permisos/" + codigo);
    }
}

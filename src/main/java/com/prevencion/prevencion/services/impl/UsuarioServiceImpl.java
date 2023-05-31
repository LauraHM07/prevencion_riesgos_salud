package com.prevencion.prevencion.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prevencion.prevencion.model.Usuario;
import com.prevencion.prevencion.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Value("${url.seguridad.rest.service}")
    String urlSeguridad;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Usuario> findAll() {
        Usuario[] usu = restTemplate.getForObject(urlSeguridad + "usuarios", Usuario[].class);
        List<Usuario> usuarios = Arrays.asList(usu);

        return usuarios;
    }

    @Override
    public Usuario findByID(int codigo) {
        Usuario usu = restTemplate.getForObject(urlSeguridad + "usuarios/" + codigo, Usuario.class);

        return usu;
    }

    @Override
    public Usuario insert(Usuario usuario) {
        Usuario usu = restTemplate.postForObject(urlSeguridad + "usuarios", usuario, Usuario.class);

        return usu;
    }

    @Override
    public void update(Usuario usuario) {
        restTemplate.put(urlSeguridad + "usuarios/" + usuario.getCodigo(), usuario);
    }

    @Override
    public void delete(int codigo) {
        restTemplate.delete(urlSeguridad + "usuarios/" + codigo);
    }

}

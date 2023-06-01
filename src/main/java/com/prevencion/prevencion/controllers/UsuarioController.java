package com.prevencion.prevencion.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prevencion.prevencion.model.Permiso;
import com.prevencion.prevencion.model.Usuario;
import com.prevencion.prevencion.services.PermisoService;
import com.prevencion.prevencion.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PermisoService permisoService;

    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        List<Usuario> usuarios = usuarioService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuarios", usuarios);

        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Usuario usuario) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.setViewName("usuarios/new");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Usuario usuario = usuarioService.findByID(codigo);
        List<Permiso> permisos = permisoService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("permisos", permisos);
        modelAndView.setViewName("usuarios/edit");

        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Usuario usuario) throws IOException {

        Usuario usu = usuarioService.insert(usuario);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + usu.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Usuario usuario,
            @RequestParam(value = "permisosSeleccionados", required = false) String[] permisosSeleccionados) {

        if (permisosSeleccionados != null) {
            List<Permiso> permisos = new ArrayList<>();

            for (String permisoCodigo : permisosSeleccionados) {
                Permiso permiso = new Permiso();
                
                permiso.setCodigo(Integer.parseInt(permisoCodigo));
                permisos.add(permiso);
            }

            usuario.setPermissions(permisos);
        } else {
            usuario.setPermissions(new ArrayList<>()); 
        }

        usuarioService.update(usuario);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + usuario.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        usuarioService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/usuarios/list");
        return modelAndView;
    }
}

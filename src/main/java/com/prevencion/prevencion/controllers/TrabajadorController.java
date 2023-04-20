package com.prevencion.prevencion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prevencion.prevencion.model.Empresa;
import com.prevencion.prevencion.model.Trabajador;
import com.prevencion.prevencion.services.EmpresaService;
import com.prevencion.prevencion.services.TrabajadorService;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    TrabajadorService trabajadorService;

    @Autowired
    EmpresaService empresaService;
    
    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");

        return modelAndView;
    }

    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {

        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
                directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Trabajador> page = trabajadorService.findAll(pageable);

        List<Trabajador> trabajadores = page.getContent();

        ModelAndView modelAndView = new ModelAndView("trabajadores/list");
        modelAndView.addObject("trabajadores", trabajadores);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @GetMapping(path = { "/create/{empresa_codigo}" })
    public ModelAndView create(Trabajador trabajador, @PathVariable(name = "empresa_codigo", required = true) int empresa_codigo) {

        Empresa empresa = empresaService.findById(empresa_codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trabajador", new Trabajador());
        modelAndView.addObject("empresa", empresa);
        modelAndView.setViewName("trabajadores/new");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}/{empresa_codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo, @PathVariable(name = "empresa_codigo", required = true) int empresa_codigo) {

        Trabajador trabajador = trabajadorService.findById(codigo);
        Empresa empresa = empresaService.findById(empresa_codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trabajador", trabajador);
        modelAndView.addObject("empresa", empresa);

        modelAndView.setViewName("trabajadores/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Trabajador trabajador, @RequestParam(name = "empresa_codigo") int codigo) {

        Empresa empresa = new Empresa();
        empresa.setCodigo(codigo);

        trabajador.setEmpresa(empresa);
        trabajadorService.insert(trabajador);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + trabajador.getCodigo() + "/" + codigo);
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Trabajador trabajador, Empresa empresa) {

        trabajadorService.update(trabajador);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + trabajador.getCodigo() + "/" + empresa.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

                trabajadorService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/trabajadores/list");
        return modelAndView;
    }
}

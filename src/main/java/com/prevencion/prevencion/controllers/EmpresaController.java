package com.prevencion.prevencion.controllers;

import java.util.ArrayList;
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
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    TrabajadorService trabajadorService;

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

        Page<Empresa> page = empresaService.findAll(pageable);

        List<Empresa> empresas = page.getContent();

        ModelAndView modelAndView = new ModelAndView("empresas/list");
        modelAndView.addObject("empresas", empresas);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Empresa empresa) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("empresa", new Empresa());
        modelAndView.setViewName("empresas/new");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Empresa empresa = empresaService.findById(codigo);
        List<Trabajador> trabajadores = trabajadorService.findAll();
        List<Trabajador> trabajadoresEmpresa = empresa.getTrabajadores();

        for (Trabajador trabajador : trabajadores) {
            for(Trabajador traEmp : trabajadoresEmpresa) {
                if(trabajador.getCodigo() == traEmp.getCodigo()) {
                    trabajador.setEmpresa(true);

                    break;
                } else {
                    trabajador.setEmpresa(false);;
                }
            }
        }

        empresa.setTrabajadores(trabajadoresEmpresa);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("empresa", empresa);
        modelAndView.addObject("trabajadores", trabajadores);
        modelAndView.setViewName("empresas/edit");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Empresa empresa) {

        empresaService.insert(empresa);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + empresa.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Empresa empresa, @RequestParam("trabajador") String[] checkboxValue) {

        empresaService.findById(empresa.getCodigo());
        List<Trabajador> trabajadores = trabajadorService.findAll();
        List<Trabajador> trabajadoresEmp = new ArrayList<Trabajador>();

        for(Trabajador trabajador : trabajadores) {
            for(int i = 0 ; i < checkboxValue.length ; i++) {
                int valor = Integer.parseInt(checkboxValue[i]);

                if(trabajador.getCodigo() == valor) {
                    trabajadoresEmp.add(trabajador);

                    break;
                }
            }
        }

        empresa.setTrabajadores(trabajadoresEmp);
        empresaService.update(empresa);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + empresa.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

                empresaService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/empresas/list");
        return modelAndView;
    }
}

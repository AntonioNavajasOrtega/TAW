package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.EmpresaRepository;
import es.taw.sampletaw.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Empresa")
public class EmpresaController {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @GetMapping("/inicioSesion")
    public String doListar(Model model){
        List<Empresa> listaEmpresas = this.empresaRepository.findAll();
        model.addAttribute("empresas", listaEmpresas);
        return "inicioSesion";
    }


}

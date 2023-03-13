package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @GetMapping("/")
    public String doListar(Model model){
        List<ClienteEntity> lista = this.clienteRepository.findAll();
        model.addAttribute("clientes", lista);
        return "adios";
    }

}

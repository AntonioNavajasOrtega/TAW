package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/cliente")
@Controller
public class ClienteController {



    @Autowired
    protected ClienteRepository clienteRepository;

    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        return "cliente";
    }

    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new Cliente(), model);
    }

    private String mostrarEditarONuevo(Cliente cliente, Model model) {
        model.addAttribute("cliente",cliente);
        return "registroCliente";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("cliente") Cliente cliente){
        this.clienteRepository.save(cliente);
        return "redirect:/cliente/?id=" + cliente.getId();
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        return this.mostrarEditarONuevo(cliente,modelo);
    }

}

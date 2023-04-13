package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.EmpresaRepository;
import es.taw.sampletaw.dao.TipoclienterelacionadoRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empresa;
import es.taw.sampletaw.entity.Tipoclienterelacionado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {


    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected ClienteRepository clienteRepository;



    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        Empresa empresa = cliente.getEmpresaByEmpresaId();
        List<Cliente> lista =  empresa.getClientesById().stream().collect(Collectors.toList());
        model.addAttribute("clientesSocios",lista);
        return "empresa";
    }

    @GetMapping("/editarEmpresa")
    public String doEditarEmpresa(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        Empresa empresa = cliente.getEmpresaByEmpresaId();
        modelo.addAttribute("empresa",empresa);
        modelo.addAttribute("cliente",cliente);
        return "editarEmpresa";
    }

    @GetMapping("/anadirCliente")
    public String doEditarCliente(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        modelo.addAttribute("empresaID",cliente.getEmpresaByEmpresaId());
        modelo.addAttribute("cliente",cliente);
        return "editarCliente";
    }


    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new Empresa(), model);
    }

    private String mostrarEditarONuevo(Empresa empresa, Model model) {

        model.addAttribute("empresa",empresa);

        return "registroEmpresa";
    }

    @GetMapping("/guardar")
    public String doGuardar(@ModelAttribute("empresa") Empresa empresa){

        this.empresaRepository.save(empresa);

        return "redirect:/empresa/nuevoCliente/?id=" + empresa.getId();
    }
    @GetMapping("/guardar2")
    public String doGuardar2(@ModelAttribute("empresa") Empresa empresa, @RequestParam("id") int id){

        this.empresaRepository.save(empresa);
        Cliente cliente = this.clienteRepository.findById(id).orElse(null);
        return "redirect:/empresa/?id=" + cliente.getId();

    }
    @GetMapping("/nuevoCliente")
    public String doNuevoCliente (Model model, @RequestParam("id") int idempresa) {
        return this.mostrarEditarONuevo2(new Cliente(), model,idempresa);
    }

    private String mostrarEditarONuevo2(Cliente cliente, Model model,int idempresa) {

        model.addAttribute("cliente",cliente);
        model.addAttribute("empresaID",this.empresaRepository.findById(idempresa).get());

        return "registroClienteEmpresa";
    }

    @GetMapping("/guardarCliente")
    public String doGuardarCliente(@ModelAttribute("cliente") Cliente cliente,@RequestParam("empresa_id") int idempresa)
    {
        Empresa empresa = this.empresaRepository.findById(idempresa).get();
        cliente.setEmpresaByEmpresaId(empresa);
        this.clienteRepository.save(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    @GetMapping("/guardarCliente2")
    public String doGuardarCliente2(@ModelAttribute("cliente") Cliente cliente,@RequestParam("empresa_id") int idempresa)
    {
        Empresa empresa = this.empresaRepository.findById(idempresa).get();
        cliente.setEmpresaByEmpresaId(empresa);
        this.clienteRepository.save(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }




}

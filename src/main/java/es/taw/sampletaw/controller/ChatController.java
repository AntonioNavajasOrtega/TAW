package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Empleado;
import es.taw.sampletaw.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/Chat")
@Controller
public class ChatController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    @GetMapping("/")
    public String doConectarChat(Model model){

        return "chatCliente";
    }

    /*@PostMapping("/enviarMensaje")
    public String enviarMensaje(@RequestParam("id") Integer idCliente, @RequestParam("id") Integer idAsistente, @RequestParam("id") Integer idContenido) {
        Cliente emisorUsuario = clienteRepository.findById(idCliente).orElse(null);
        Empleado receptorUsuario = empleadoRepository.findById(idAsistente).orElse(null);
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido();
        mensajeRepository.save(mensaje);
        return "redirect:/chat?emisor=" + emisor + "&receptor=" + receptor;
    }

    @GetMapping("/recibirMensajes")
    @ResponseBody
    public List<Mensaje> recibirMensajes(@RequestParam("id") Integer emisor, @RequestParam("id") Integer receptor) {
        Cliente emisorCliente = clienteRepository.findById(emisor).orElse(null);
        Empleado receptorEmpleado = empleadoRepository.findById(receptor).orElse(null);
        return mensajeRepository.findByEmisorAndReceptor(emisorUsuario, receptorUsuario);
    }*/

}

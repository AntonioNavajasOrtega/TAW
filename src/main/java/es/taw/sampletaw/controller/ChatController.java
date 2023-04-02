package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/chat")
@Controller
public class ChatController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ConversacionRepository conversacionRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    @GetMapping("/nuevo")
    public String crearNuevoChat(@RequestParam("idCliente") Integer idCliente,@RequestParam("asunto") String asunto, Model model){
        Conversacion chat = new Conversacion();
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);

        //Revisar como pillar el asistente para setearlo a la conver
        Empleado asistente = this.empleadoRepository.findById(1).orElse(null);
        //Empleado asistente = this.empleadoRepository.findAsistente();
        chat.setAbierta((byte)1);
        chat.setClienteByCliente(cliente);
        chat.setEmpleadoByEmpleado(asistente);
        //chat.setAsunto(asunto);

        this.conversacionRepository.save(chat);
        model.addAttribute("chat", chat);

        return "chatCliente";
    }

    @GetMapping("/cerrar")
    public String cerrarConversacion(@RequestParam("idConversacion") Integer idConversacion){
        Conversacion conv = this.conversacionRepository.findById(idConversacion).orElse(null);
        conv.setAbierta((byte)0);
        conv.getClienteByCliente().getConversacionsById().add(conv);
        this.conversacionRepository.save(conv);
        this.clienteRepository.save(conv.getClienteByCliente());

        return "redirect:/cliente/?id="+conv.getClienteByCliente().getId();
    }

    @GetMapping("/asistente")
    public String doConectarChatAsistente(@RequestParam("idConversacion") Integer idConversacion ,Model model){
        Conversacion conv = this.conversacionRepository.findById(idConversacion).orElse(null);
        model.addAttribute("conversacion", conv);

        return "chatAsistente";
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

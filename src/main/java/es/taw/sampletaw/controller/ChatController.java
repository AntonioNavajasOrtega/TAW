package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.entity.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    @Autowired
    private MensajeRepository mensajeRepository;

    @GetMapping("/listar")
    public String doListarChat(@RequestParam("idChat") Integer idChat,
                               @RequestParam("soyAsistente") Byte soyAsistente , Model model){
        Conversacion conversacion = this.conversacionRepository.findById(idChat).orElse(null);
        model.addAttribute("chat", conversacion);
        model.addAttribute("soyAsistente", soyAsistente);
        return "chat";
    }
    @GetMapping("/nuevo")
    public String crearNuevoChat(@RequestParam("idCliente") Integer idCliente,@RequestParam("asunto") String asunto, Model model){
        Conversacion chat = new Conversacion();
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        Empleado asistente = this.empleadoRepository.findById(2).orElse(null);
        chat.setAbierta((byte)1);
        chat.setClienteByCliente(cliente);
        chat.setEmpleadoByEmpleado(asistente);
        Timestamp fechaApertura = new Timestamp(System.currentTimeMillis());
        chat.setFechaApertura(fechaApertura);
        chat.setAsunto(asunto);

        this.conversacionRepository.save(chat);
        model.addAttribute("chat", chat);
        model.addAttribute("soyAsistente", 0);
        return "redirect:/chat/listar?idChat=" + chat.getId() + "&soyAsistente=" + 0;
    }

    @GetMapping("/cerrar")
    public String cerrarConversacion(@RequestParam("idConversacion") Integer idConversacion){
        Conversacion conv = this.conversacionRepository.findById(idConversacion).orElse(null);
        conv.setAbierta((byte)0);
        Timestamp fechaCierre = new Timestamp(System.currentTimeMillis());
        conv.setFechaCierre(fechaCierre);
        conv.getClienteByCliente().getConversacionsById().add(conv);
        this.conversacionRepository.save(conv);
        this.clienteRepository.save(conv.getClienteByCliente());

        if(conv.getClienteByCliente().getEmpresaByEmpresaId() == null)
        {
            return "redirect:/cliente/?id="+conv.getClienteByCliente().getId();
        }
        else
        {
            return "redirect:/empresa/?id="+conv.getClienteByCliente().getId();
        }

    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@RequestParam("idChat") Integer idChat, @RequestParam("mensaje") String textoMensaje,
                                @RequestParam("soyAsistente") Byte soyAsistente) {
        Conversacion conversacion = this.conversacionRepository.findById(idChat).orElse(null);
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(textoMensaje);
        Timestamp fechaMsg = new Timestamp(System.currentTimeMillis());
        mensaje.setFecha(fechaMsg);
        if(soyAsistente == 0){
            mensaje.setClienteByEmisorCliente(conversacion.getClienteByCliente());
            mensaje.setEmpleadoByReceptorEmpleado(conversacion.getEmpleadoByEmpleado());
        }else{
            mensaje.setClienteByReceptorCliente(conversacion.getClienteByCliente());
            mensaje.setEmpleadoByEmisorEmpleado(conversacion.getEmpleadoByEmpleado());
        }
        mensaje.setConversacionByConversacion(conversacion);
        mensajeRepository.save(mensaje);

        return "redirect:/chat/listar?idChat=" + idChat + "&soyAsistente=" + (soyAsistente == 0 ? 0 : 1) ;
    }


}

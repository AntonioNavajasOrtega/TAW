package es.taw.sampletaw.controller;

import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.service.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RequestMapping("/chat")
@Controller
public class ChatController {
    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected EmpleadoService empleadoService;
    @Autowired
    protected ConversacionService conversacionService;

    @Autowired
    protected TipoEmpleadoService tipoEmpleadoService;
    @Autowired
    protected MensajeService mensajeService;

    @GetMapping("/listar")
    public String doListarChat(@RequestParam("idChat") Integer idChat,
                               @RequestParam("soyAsistente") Byte soyAsistente , Model model){
        ConversacionDTO conversacion = this.conversacionService.buscarConversacion(idChat);
        List<MensajeDTO> mensajes = this.mensajeService.listarMensajes(conversacion);

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("chat", conversacion);
        model.addAttribute("soyAsistente", soyAsistente);
        return "chat";
    }
    @GetMapping("/nuevo")
    public String crearNuevoChat(@RequestParam("idCliente") Integer idCliente,@RequestParam("asunto") String asunto, Model model){
        ConversacionDTO chat = this.conversacionService.iniciarChat(idCliente,asunto);

        this.conversacionService.guardarOEditar(chat);


        model.addAttribute("chat", chat);
        model.addAttribute("soyAsistente", 0);
        return "redirect:/chat/listar?idChat=" + chat.getId() + "&soyAsistente=" + 0;
    }

    @GetMapping("/cerrar")
    public String cerrarConversacion(@RequestParam("idConversacion") Integer idConversacion){
        ConversacionDTO conv = this.conversacionService.buscarConversacion(idConversacion);
        this.conversacionService.cerrarConversacion(conv);

        this.conversacionService.guardarOEditar(conv);

        if(conv.getCliente().getEmpresa() == null)
        {
            return "redirect:/cliente/?id="+conv.getCliente().getId();
        }
        else
        {
            return "redirect:/empresa/?id="+conv.getCliente().getId();
        }

    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@RequestParam("idChat") Integer idChat, @RequestParam("mensaje") String textoMensaje,
                                @RequestParam("soyAsistente") Byte soyAsistente) {
        ConversacionDTO conversacion = this.conversacionService.buscarConversacion(idChat);
        MensajeDTO mensaje = this.mensajeService.crearMensaje(idChat,textoMensaje,soyAsistente);

        this.mensajeService.guardar(mensaje);
        return "redirect:/chat/listar?idChat=" + idChat + "&soyAsistente=" + (soyAsistente == 0 ? 0 : 1) ;
    }


}

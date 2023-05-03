package es.taw.sampletaw.controller;

import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/chat")
@Controller
public class ChatController {
    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected ConversacionService conversacionService;

    @Autowired
    protected TipoEmpleadoService tipoEmpleadoService;
    @Autowired
    protected MensajeService mensajeService;

    @GetMapping("/listar")
    public String doListarChat(@RequestParam("idChat") Integer idChat,
                               @RequestParam("soyAsistente") Byte soyAsistente , Model model, HttpSession session){

        String urlTo;
        EmpleadoDTO asistenteSession = (EmpleadoDTO) session.getAttribute("asistente");
        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        ConversacionDTO conversacion = this.conversacionService.buscarConversacion(idChat);
        if (soyAsistente == 1 && asistenteSession == null) {
            urlTo = "redirect:/logout";
        } else if (soyAsistente == 0 && (clienteSession == null || clienteSession.getId() != conversacion.getCliente().getId())){
            urlTo = "redirect:/logout";
        } else {
            List<MensajeDTO> mensajes = this.mensajeService.listarMensajes(conversacion);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("chat", conversacion);
            model.addAttribute("soyAsistente", soyAsistente);
            urlTo = "chat";
        }

        return urlTo;
    }
    @GetMapping("/nuevo")
    public String crearNuevoChat(@RequestParam("idCliente") Integer idCliente,@RequestParam("asunto") String asunto, Model model, HttpSession session){
        String urlTo;
        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        if(clienteSession==null || clienteSession.getId()!=idCliente){
            urlTo = "redirect:/logout";
        }else{
            ConversacionDTO chat = this.conversacionService.iniciarChat(idCliente,asunto);
            this.conversacionService.guardarOEditar(chat);

            model.addAttribute("chat", chat);
            model.addAttribute("soyAsistente", 0);
            urlTo = "redirect:/chat/listar?idChat=" + chat.getId() + "&soyAsistente=" + 0;
        }
        return urlTo;
    }

    @GetMapping("/cerrar")
    public String cerrarConversacion(@RequestParam("idConversacion") Integer idConversacion, HttpSession session){
        String urlTo;
        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        ConversacionDTO conv = this.conversacionService.buscarConversacion(idConversacion);

        if(clienteSession==null || clienteSession.getId()!=conv.getCliente().getId()){
            urlTo = "redirect:/logout";
        }else{
            this.conversacionService.cerrarConversacion(conv);

            this.conversacionService.guardarOEditar(conv);
            if(conv.getCliente().getEmpresa() == null)
            {
                urlTo = "redirect:/cliente/?id="+conv.getCliente().getId();
            }
            else
            {
                urlTo = "redirect:/empresa/?id="+conv.getCliente().getId();
            }

        }

        return  urlTo;

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

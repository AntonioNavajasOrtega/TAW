package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.entity.Mensaje;
import es.taw.sampletaw.service.AsistenteService;
import es.taw.sampletaw.service.ClienteService;
import es.taw.sampletaw.service.ConversacionService;
import es.taw.sampletaw.service.MensajeService;
import es.taw.sampletaw.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/asistente")
@Controller
public class AsistenteController {


    @Autowired
    protected AsistenteService asistenteService;

    @Autowired
    protected ConversacionService conversacionService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected MensajeService mensajeService;

    @GetMapping("/")
    public String doListarConversaciones(Model model, HttpSession session){
        return this.procesarFiltro(null,model,session);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroAsistente filtro, Model model, HttpSession session){
        return this.procesarFiltro(filtro,model,session);
    }

    protected String procesarFiltro(FiltroAsistente filtro, Model model, HttpSession session){
        String urlTo = "conversaciones";
        List<ConversacionDTO> conversaciones;
        EmpleadoDTO asistente = (EmpleadoDTO) session.getAttribute("asistente");

        if (asistente == null) {
            urlTo = "login";
        } else {
            if (filtro == null || filtro.getFechaApertura().isEmpty() && filtro.getUsuario().isEmpty() && filtro.getNumeroMensajes().isEmpty()) {
                conversaciones = this.conversacionService.listarConversaciones();
                filtro = new FiltroAsistente();
            } else {
                conversaciones = this.conversacionService.listarConversaciones(filtro.getUsuario(),filtro.getFechaApertura(),filtro.getNumeroMensajes());
            }


            model.addAttribute("filtro", filtro);
            model.addAttribute("conversaciones", conversaciones);
            List<ClienteDTO> usuarios = this.clienteService.listarClientesConMensajes();
            model.addAttribute("usuarios", usuarios);
        }

        return urlTo;
    }

    @GetMapping("/mensajesUsuario")
    public String doListarMensajesIntercambiados(@RequestParam("idUsuario") Integer idCliente, Model model, HttpSession session){
        List<MensajeDTO> mensajeList = this.mensajeService.buscarMensajesCuyoUsuarioEsEmisorOReceptor(idCliente);
        model.addAttribute("mensajes", mensajeList);
        return "mensajes";
    }


}

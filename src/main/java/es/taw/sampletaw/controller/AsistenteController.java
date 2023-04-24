package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empleado;
import es.taw.sampletaw.entity.Mensaje;
import es.taw.sampletaw.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@RequestMapping("/asistente")
@Controller
public class AsistenteController {
    @Autowired
    private ConversacionRepository conversacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

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
        List<Conversacion> conversaciones;
        Empleado asistente = (Empleado) session.getAttribute("asistente");

        if (asistente == null) {
            urlTo = "login";
        } else {
            if (filtro == null || filtro.getFechaApertura().isEmpty() && filtro.getUsuario().isEmpty() && filtro.getNumeroMensajes().isEmpty()) {
                conversaciones = this.conversacionRepository.findAll();
                filtro = new FiltroAsistente();
            } else if (filtro.getNumeroMensajes().equals("asc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().isEmpty()) {
                conversaciones = this.conversacionRepository.ordenPorNumeroMensajesAsc();
            } else if (filtro.getNumeroMensajes().equals("desc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().isEmpty()) {
                conversaciones = this.conversacionRepository.ordenPorNumeroMensajesDesc();
            } else if (filtro.getNumeroMensajes().isEmpty() && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().isEmpty()) {
                conversaciones = this.conversacionRepository.buscaPorUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().isEmpty() && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenPorFechaAperturaAsc();
            } else if (filtro.getNumeroMensajes().isEmpty() && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenPorFechaAperturaDesc();
            } else if (filtro.getNumeroMensajes().equals("desc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDesc();
            } else if (filtro.getNumeroMensajes().equals("asc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAsc();
            } else if (filtro.getNumeroMensajes().equals("asc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAsc();
            } else if (filtro.getNumeroMensajes().equals("desc") && filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDesc();
            } else if (filtro.getNumeroMensajes().equals("desc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDescYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("asc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAscYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("asc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAscYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("desc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDescYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("asc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().isEmpty()) {
                conversaciones = this.conversacionRepository.ordenPorNumeroMensajesAscYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("des") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().isEmpty()) {
                conversaciones = this.conversacionRepository.ordenPorNumeroMensajesDescYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().isEmpty() && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenPorFechaAperturaAscYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().isEmpty() && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenPorFechaAperturaDescYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("desc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDescYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("asc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("asc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAscYBuscaUsuario(filtro.getUsuario());
            } else if (filtro.getNumeroMensajes().equals("asc") && !filtro.getUsuario().isEmpty() && filtro.getFechaApertura().equals("desc")) {
                conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAscYBuscaUsuario(filtro.getUsuario());
            } else {
                conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDescYBuscaUsuario(filtro.getUsuario());
            }

            model.addAttribute("filtro", filtro);
            model.addAttribute("conversaciones", conversaciones);
            List<Cliente> usuarios = this.clienteRepository.findAll();
            model.addAttribute("usuarios", usuarios);
        }

        return urlTo;
    }

    @GetMapping("/mensajesUsuario")
    public String doListarMensajesIntercambiados(@RequestParam("idUsuario") Integer idCliente, Model model, HttpSession session){
        Cliente usuario = this.clienteRepository.findById(idCliente).orElse(null);
        List<Mensaje> mensajeList = this.mensajeRepository.mensajesCuyoUsuarioEsEmisorOReceptor(usuario);
        model.addAttribute("mensajes", mensajeList);
        return "mensajes";
    }


}

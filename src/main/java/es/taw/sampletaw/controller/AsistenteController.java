package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.entity.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/asistente")
@Controller
public class AsistenteController {
    @Autowired
    private ConversacionRepository conversacionRepository;

    @GetMapping("/")
    public String doListarConversaciones(Model model){
        List<Conversacion> conversaciones = conversacionRepository.findAll();
        model.addAttribute("conversaciones", conversaciones);
        return "conversaciones";
    }



}

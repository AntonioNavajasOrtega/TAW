package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.entity.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/Empleado")
@Controller
public class EmpleadoController {
    @Autowired
    private ConversacionRepository conversacionRepository;

    @GetMapping("/conversaciones")
    public String doListarConversaciones(Model model){
        List<Conversacion> conversaciones = conversacionRepository.findAll();
        model.addAttribute("conversaciones", conversaciones);
        return "conversaciones";
    }



}

package es.taw.sampletaw.controller;
import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @GetMapping("/")
    public String doLogin() {
        return "login";
    }

    @PostMapping("/")
    public String doAutenticar (@RequestParam("email") String email,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        String urlTo = "redirect:/cliente/";
        Cliente cliente = this.clienteRepository.autenticar(email, contrasena);
        Empleado empleado = this.empleadoRepository.autenticar(email, contrasena);
        if (cliente == null && empleado == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        }else if (empleado != null) {
            if(empleado.getTipoEmpleadoByTipo().getTipo().equalsIgnoreCase("asistente")){
                urlTo = "redirect:/asistente/";
            }

        } else{
            session.setAttribute("cliente",cliente);
            urlTo += "?id=" + cliente.getId();
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}

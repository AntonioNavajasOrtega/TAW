package es.taw.sampletaw.controller;
import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Empleado;
import es.taw.sampletaw.service.LoginService;
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

    @Autowired
    protected LoginService loginService;

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
        EmpleadoDTO asistente = this.loginService.autenticar(email, contrasena);
        if (cliente == null && asistente == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        }else if (asistente != null) {
            if(asistente.getTipoEmpleado().getTipo().equalsIgnoreCase("asistente")){
                session.setAttribute("asistente", asistente);
                urlTo = "redirect:/asistente/";
            }

        } else{
            session.setAttribute("clienteSession",cliente);
            if(cliente.getEmpresaByEmpresaId() == null)
            {
                urlTo += "?id=" + cliente.getId();
            }
            else
            {
                 urlTo = "redirect:/empresa/";
                urlTo += "?id=" + cliente.getId();
            }

        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}

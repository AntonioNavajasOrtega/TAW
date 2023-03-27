package es.taw.sampletaw.controller;
import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @GetMapping("/")
    public String doLogin() {
        return "login";
    }

    @PostMapping("/")
    public String doAutenticar (@RequestParam("email") String email,
                                @RequestParam("contrasena") String contrasena,
                                Model model) {
        String urlTo = "redirect:/customer/";
        Cliente admin = this.clienteRepository.autenticar(email, contrasena);
        if (admin == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        }

        return urlTo;
    }

}

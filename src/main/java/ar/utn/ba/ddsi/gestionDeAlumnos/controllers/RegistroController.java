package ar.utn.ba.ddsi.gestionDeAlumnos.controllers;

import ar.utn.ba.ddsi.gestionDeAlumnos.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
         model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "login/registro";
    }

    // Aquí también iría el método @PostMapping("/registro") para procesar los datos
    // cuando el usuario envíe el formulario.
}
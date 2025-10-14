package ar.utn.ba.ddsi.gestionDeAlumnos.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LegalController {

  @GetMapping("/privacidad")
  public String mostrarPaginaDePrivacidad(Model model) {
    model.addAttribute("titulo", "Información Legal y de Privacidad");

    return "privacidad/privacidad";
  }
}
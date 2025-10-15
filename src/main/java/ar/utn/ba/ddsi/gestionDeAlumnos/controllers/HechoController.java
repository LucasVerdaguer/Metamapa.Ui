package ar.utn.ba.ddsi.gestionDeAlumnos.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HechoController {
    @GetMapping("/hechos")
    public String hecho(){
        return "hechos/hechos";
    }
}

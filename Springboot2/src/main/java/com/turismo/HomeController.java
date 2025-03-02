package com.turismo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar la raíz de la API y redirigir a /hoteles.
 */
@Controller
public class HomeController {

    /**
     * Redirige automáticamente a /hoteles cuando se accede a /
     * @return Redirección a /hoteles
     */
    @GetMapping("/")
    public String redirectToHoteles() {
        return "redirect:/hoteles";
    }
}

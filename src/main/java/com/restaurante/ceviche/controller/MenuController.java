package com.restaurante.ceviche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.restaurante.ceviche.model.entidad.Usuario;

@Controller
@SessionAttributes({"usuario", "rolUsuario", "nombreTrabajador"}) // Mantener datos en sesión
public class MenuController {

    @GetMapping("/inicio")
    public String inicio(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuario != null) {
            model.addAttribute("rolUsuario", usuario.getRol().getTipoRol());
            model.addAttribute("nombreTrabajador", usuario.getTrabajador().getNombre());
        }
        return "Menu/inicio"; // Cargar vista del menú principal
    }

    @GetMapping("/gestionar-empleados")
    public String gestionarEmpleados() {
        return "redirect:/empleados";
    }

    @GetMapping("/gestionar-productos")
    public String gestionarProductos() {
        return "redirect:/productos";
    }

    @GetMapping("/gestionar-perfiles")
    public String gestionarPerfiles() {
        return "redirect:/perfiles";
    }
    @GetMapping("/gestionar-ordenes")
    public String gestionarOrdenes() {
        return "redirect:/ordenes";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Limpiar sesión
        return "redirect:/";
    }
}

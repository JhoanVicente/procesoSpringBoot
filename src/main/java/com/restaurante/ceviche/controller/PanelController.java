package com.restaurante.ceviche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.restaurante.ceviche.model.entidad.Usuario;
import com.restaurante.ceviche.model.service.serviceEmpleado.usuario.IUsuarioService;

@Controller
@SessionAttributes({"usuario", "rolUsuario"})
public class PanelController {

        @Autowired
        private IUsuarioService usuarioService;
        @PostMapping("/login")
        public String login(@RequestParam("correo") String correo,
                            @RequestParam("password") String password,
                            Model model) {
            Usuario usuario = usuarioService.getUsuarioByCorreo(correo);
            if (usuario != null && usuario.getPassword().equals(password)) {
                model.addAttribute("usuario", usuario);
                model.addAttribute("rolUsuario", usuario.getRol().getTipoRol());
                return "redirect:/inicio";
            } else {
                model.addAttribute("error", "Correo o contraseña incorrectos.");
                return "index";
            }
        }

}

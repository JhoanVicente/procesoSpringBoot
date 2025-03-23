package com.restaurante.ceviche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.restaurante.ceviche.model.entidad.Usuario;
import com.restaurante.ceviche.model.service.serviceEmpleado.usuario.IUsuarioService;
@Controller
@SessionAttributes({"usuario", "rolUsuario"}) // Persistir datos en la sesión
public class PerfilController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/perfiles")
    public String mostrarPerfil(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuario != null) {
            model.addAttribute("usuario", usuario); // Datos del usuario ya están en sesión
            return "Empleado/Perfil"; // Cargar el archivo templates/Empleado/Perfil.html
        } else {
            model.addAttribute("error", "Usuario no encontrado.");
            return "redirect:/"; // Redirigir al inicio si hay un error
        }
    }

    // Guardar los cambios del perfil del usuario
    @PostMapping("/perfil/guardar")
    public String guardarPerfil(@ModelAttribute("usuario") Usuario usuario,
                                @RequestParam("nombre") String nombre,
                                @RequestParam("apellido") String apellido,
                                @RequestParam("dni") String dni,
                                @RequestParam("telefono") String telefono,
                                @RequestParam("direccion") String direccion,
                                Model model) {
        if (usuario != null) {
            // Actualizar los datos del usuario
            usuario.getTrabajador().setNombre(nombre);
            usuario.getTrabajador().setApellido(apellido);
            usuario.getTrabajador().setDni(dni);
            usuario.getTrabajador().setTelefono(telefono);
            usuario.getTrabajador().setDireccion(direccion);

            // Guardar los cambios
            usuarioService.saveUsuario(usuario);

            model.addAttribute("success", "Perfil actualizado correctamente.");
            return "redirect:/perfiles"; // Recargar el perfil actualizado
        } else {
            model.addAttribute("error", "Error al actualizar el perfil.");
            return "Empleado/Perfil"; // Regresar a la página del perfil si falla
        }
    }
}

package com.restaurante.ceviche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.restaurante.ceviche.model.entidad.Rol;
import com.restaurante.ceviche.model.entidad.Trabajador;
import com.restaurante.ceviche.model.entidad.Usuario;
import com.restaurante.ceviche.model.service.serviceEmpleado.rol.IRolService;
import com.restaurante.ceviche.model.service.serviceEmpleado.trabajador.ITrabajadorService;
import com.restaurante.ceviche.model.service.serviceEmpleado.usuario.IUsuarioService;

import java.util.List;

@Controller
@RequestMapping("/empleados")
@SessionAttributes({"usuario", "rolUsuario"}) // Para conservar el estado del usuario logeado
public class EmpleadoController {

    @Autowired
    private ITrabajadorService trabajadorService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Trabajador> trabajadores = trabajadorService.getAllTrabajadores();
        List<Rol> roles = rolService.getAllRoles();

        model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("roles", roles);
        model.addAttribute("nuevoTrabajador", new Trabajador());
        return "Empleado/Inicial";
    }

    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute("nuevoTrabajador") Trabajador trabajador,
                                @RequestParam("correo") String correo,
                                @RequestParam("password") String password,
                                @RequestParam("rolId") Integer rolId,
                                Model model) {
        if (trabajadorService.getTrabajadorByDni(trabajador.getDni()) != null) {
            model.addAttribute("error", "El DNI ya está registrado. Por favor, utiliza otro.");
            cargarDatosFormulario(model);
            return "Empleado/Inicial";
        }

        Trabajador trabajadorGuardado = trabajadorService.saveTrabajador(trabajador);
        Rol rol = rolService.getRolById(rolId);
        Usuario usuario = new Usuario(correo, password, trabajadorGuardado, rol);
        usuarioService.saveUsuario(usuario);

        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Integer id, Model model) {
        Trabajador trabajador = trabajadorService.getTrabajadorById(id);
        List<Rol> roles = rolService.getAllRoles();

        if (trabajador != null) {
            model.addAttribute("trabajador", trabajador);
            model.addAttribute("roles", roles);
            return "Empleado/editar";
        }

        return "redirect:/empleados";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable("id") Integer id,
                                     @ModelAttribute("trabajador") Trabajador trabajadorActualizado,
                                     @RequestParam("correo") String correo,
                                     @RequestParam("password") String password,
                                     @RequestParam("rolId") Integer rolId,
                                     Model model) {
        Trabajador existente = trabajadorService.getTrabajadorById(id);
        if (existente == null) {
            model.addAttribute("error", "El trabajador no existe.");
            return "redirect:/empleados";
        }

        Trabajador duplicado = trabajadorService.getTrabajadorByDni(trabajadorActualizado.getDni());
        if (duplicado != null && !duplicado.getIdTrabajador().equals(id)) {
            model.addAttribute("error", "El DNI ya está registrado en otro trabajador.");
            model.addAttribute("trabajador", existente);
            model.addAttribute("roles", rolService.getAllRoles());
            return "Empleado/editar";
        }

        existente.setNombre(trabajadorActualizado.getNombre());
        existente.setApellido(trabajadorActualizado.getApellido());
        existente.setDni(trabajadorActualizado.getDni());
        existente.setDireccion(trabajadorActualizado.getDireccion());
        existente.setTelefono(trabajadorActualizado.getTelefono());
        trabajadorService.saveTrabajador(existente);

        Usuario usuario = usuarioService.getUsuarioByCorreo(correo);
        if (usuario != null) {
            usuario.setPassword(password);
            usuario.setRol(rolService.getRolById(rolId));
            usuarioService.saveUsuario(usuario);
        }

        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Integer id) {
        trabajadorService.deleteTrabajador(id);
        return "redirect:/empleados";
    }

    private void cargarDatosFormulario(Model model) {
        model.addAttribute("trabajadores", trabajadorService.getAllTrabajadores());
        model.addAttribute("roles", rolService.getAllRoles());
    }
}

package com.restaurante.ceviche.controller;

import com.restaurante.ceviche.model.entidad.DetallesOrden;
import com.restaurante.ceviche.model.service.serviceOrden.detallesorden.IDetallesOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/detalleorden")
public class DetalleOrdenController {

    @Autowired
    private IDetallesOrdenService detallesOrdenService;

    // Listar todos los detalles de orden
    @GetMapping("/iniciodetalle")
    public String listarDetallesOrden(Model model) {
        List<DetallesOrden> detallesOrdenList = detallesOrdenService.findAll();
        model.addAttribute("detallesOrdenList", detallesOrdenList);
        return "detalleorden/iniciodetalle";
    }

    // Mostrar formulario para crear un nuevo detalle de orden
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("detallesOrden", new DetallesOrden());  // Asegúrate de inicializar el objeto
        return "detalleorden/formulario";
    }

    // Guardar un nuevo detalle de orden
    @PostMapping("/guardar")
    public String guardarDetalleOrden(@ModelAttribute DetallesOrden detallesOrden, RedirectAttributes redirectAttributes) {
        detallesOrdenService.save(detallesOrden);
        redirectAttributes.addFlashAttribute("mensaje", "Detalle de orden guardado con éxito.");
        return "redirect:/detalleorden/iniciodetalle";
    }

    // Mostrar formulario para editar un detalle de orden
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<DetallesOrden> detallesOrdenOptional = detallesOrdenService.findById(id);
        if (detallesOrdenOptional.isPresent()) {
            DetallesOrden detallesOrden = detallesOrdenOptional.get();
            model.addAttribute("detallesOrden", detallesOrden); // Asegúrate de que el modelo tenga los datos
            return "detalleorden/formulario";
        } else {
            redirectAttributes.addFlashAttribute("error", "Detalle de orden no encontrado.");
            return "redirect:/detalleorden/iniciodetalle";
        }
    }

    // Eliminar un detalle de orden
    @GetMapping("/eliminar/{id}")
    public String eliminarDetalleOrden(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        detallesOrdenService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Detalle de orden eliminado con éxito.");
        return "redirect:/detalleorden/iniciodetalle";
    }
}

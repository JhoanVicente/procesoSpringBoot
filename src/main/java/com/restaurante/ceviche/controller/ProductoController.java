package com.restaurante.ceviche.controller;

import com.restaurante.ceviche.model.entidad.Categoria;
import com.restaurante.ceviche.model.entidad.Inventario;
import com.restaurante.ceviche.model.entidad.Producto;
import com.restaurante.ceviche.model.service.serviceProducto.categoria.ICategoriaService;
import com.restaurante.ceviche.model.service.serviceProducto.inventario.IInventarioService;
import com.restaurante.ceviche.model.service.serviceProducto.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/productos")
@SessionAttributes({"usuario", "rolUsuario"}) // Para conservar el estado del usuario logeado
public class ProductoController {

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IInventarioService inventarioService;

    @GetMapping
    public String mostrarInventario(Model model) {
        List<Categoria> categorias = categoriaService.listarTodas();
        List<Producto> productos = productoService.listarTodo();
        List<Inventario> inventarios = inventarioService.listarTodo();

        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        model.addAttribute("inventarios", inventarios);
        model.addAttribute("nuevaCategoria", new Categoria());
        model.addAttribute("nuevoProducto", new Producto());
        model.addAttribute("nuevoInventario", new Inventario());

        return "Producto/inventario";
    }

    @PostMapping("/categoria/crear")
    public String crearCategoria(@ModelAttribute Categoria nuevaCategoria, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.guardar(nuevaCategoria);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría creada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la categoría: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @PostMapping("/producto/crear")
    public String crearProductoConInventario(
            @ModelAttribute Producto nuevoProducto,
            @RequestParam Integer categoriaId,
            @RequestParam Integer cantidad,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Categoria categoria = categoriaService.buscarPorId(categoriaId).orElse(null);
            if (categoria == null) {
                redirectAttributes.addFlashAttribute("error", "Categoría no encontrada.");
                return "redirect:/productos";
            }

            nuevoProducto.setCategoria(categoria);

            if (nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                redirectAttributes.addFlashAttribute("error", "El precio debe ser mayor a 0.");
                return "redirect:/productos";
            }

            if (cantidad <= 0) {
                redirectAttributes.addFlashAttribute("error", "La cantidad debe ser mayor a 0.");
                return "redirect:/productos";
            }

            Producto productoGuardado = productoService.guardar(nuevoProducto);

            Inventario nuevoInventario = new Inventario();
            nuevoInventario.setProducto(productoGuardado);
            nuevoInventario.setCantidad(cantidad);
            nuevoInventario.setFechaLlegada(java.time.LocalDateTime.now());
            inventarioService.guardar(nuevoInventario);

            redirectAttributes.addFlashAttribute("mensaje", "Producto e inventario creados exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el producto o inventario: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/categoria/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la categoría: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminarPorId(id);
            redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/producto/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarPorId(id).orElse(null);
        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado.");
            return "redirect:/productos";
        }

        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);

        return "Producto/editarProducto";
    }

    @PostMapping("/producto/editar")
    public String actualizarProducto(@ModelAttribute Producto productoActualizado, @RequestParam Integer categoriaId, RedirectAttributes redirectAttributes) {
        try {
            Categoria categoria = categoriaService.buscarPorId(categoriaId).orElse(null);
            if (categoria == null) {
                redirectAttributes.addFlashAttribute("error", "Categoría no encontrada.");
                return "redirect:/productos";
            }

            productoActualizado.setCategoria(categoria);
            productoService.guardar(productoActualizado);

            redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }
}

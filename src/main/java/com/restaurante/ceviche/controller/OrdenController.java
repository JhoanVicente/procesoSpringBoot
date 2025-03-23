package com.restaurante.ceviche.controller;

import com.restaurante.ceviche.model.entidad.*;
import com.restaurante.ceviche.model.service.serviceOrden.cliente.IClienteService;
import com.restaurante.ceviche.model.service.serviceOrden.detallesorden.IDetallesOrdenService;
import com.restaurante.ceviche.model.service.serviceOrden.oden.IOrdenService;
import com.restaurante.ceviche.model.service.serviceProducto.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDetallesOrdenService detallesOrdenService;

    // Listar todas las órdenes
    @GetMapping
    public String listarOrdenes(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        List<Orden> ordenes = ordenService.findAll();
        model.addAttribute("ordenes", ordenes);
        model.addAttribute("trabajador", usuario.getTrabajador());
        return "orden/desarrollo";
    }

    // Mostrar formulario para crear una nueva orden
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/"; // Redirigir al login si no hay sesión
        }

        // Llama al servicio para obtener todos los productos
        List<Producto> productos = productoService.listarTodo(); 
        List<Cliente> clientes = clienteService.findAll();

        model.addAttribute("trabajador", usuario.getTrabajador());
        model.addAttribute("productos", productos); // Asegúrate de que esta lista se pase correctamente
        model.addAttribute("clientes", clientes);
        model.addAttribute("orden", new Orden());

        return "orden/desarrollo";
    }

    // Guardar una nueva orden
    @PostMapping("/guardar")
    public String guardarOrden(
            @ModelAttribute("orden") Orden orden,
            @RequestParam("nombreCliente") String nombreCliente,
            @RequestParam("emailCliente") String emailCliente,
            @RequestParam("productos") List<Integer> idProductos, // Ahora es una lista de productos seleccionados
            @RequestParam("cantidad") List<Integer> cantidades, // Cantidades para cada producto
            @RequestParam("numeroMesa") Integer numeroMesa,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/";
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(nombreCliente);
        cliente.setEmail(emailCliente);
        clienteService.save(cliente);

        orden.setCliente(cliente);
        orden.setTrabajador(usuario.getTrabajador());
        orden.setNumeroMesa(numeroMesa);
        orden.setTotal(BigDecimal.ZERO);
        ordenService.save(orden);

        // Iterar a través de los productos seleccionados
        for (int i = 0; i < idProductos.size(); i++) {
            Integer idProducto = idProductos.get(i);
            Integer cantidad = cantidades.get(i);

            Optional<Producto> producto = productoService.buscarPorId(idProducto);
            if (producto.isPresent()) {
                Producto prod = producto.get();
                DetallesOrden detallesOrden = new DetallesOrden();
                detallesOrden.setOrden(orden);
                detallesOrden.setProducto(prod);
                detallesOrden.setCantidad(cantidad);
                detallesOrden.setPrecioUnitario(prod.getPrecio());
                
                detallesOrdenService.save(detallesOrden);

  
            } else {
                model.addAttribute("error", "Producto no encontrado.");
                return "orden/desarrollo";
            }
        }

        ordenService.save(orden); // Aseguramos que la orden con el total actualizado se guarde correctamente

        return "redirect:/ordenes";
    }

}

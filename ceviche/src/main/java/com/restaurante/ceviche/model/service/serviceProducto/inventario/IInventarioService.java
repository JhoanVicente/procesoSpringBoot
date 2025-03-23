package com.restaurante.ceviche.model.service.serviceProducto.inventario;

import com.restaurante.ceviche.model.entidad.Inventario;

import java.util.List;
import java.util.Optional;

public interface IInventarioService {
    // Listar todo el inventario
    List<Inventario> listarTodo();

    // Buscar por ID de inventario
    Optional<Inventario> buscarPorId(Integer id);

    // Buscar por ID de producto
    List<Inventario> buscarPorProductoId(Integer idProducto);

    // Guardar o actualizar un inventario
    Inventario guardar(Inventario inventario);

    // Eliminar inventario por ID
    void eliminarPorId(Integer id);
}
package com.restaurante.ceviche.model.service.serviceProducto.producto;

import com.restaurante.ceviche.model.entidad.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    // Listar todos los productos
    List<Producto> listarTodo();

    // Buscar producto por ID
    Optional<Producto> buscarPorId(Integer id);

    // Guardar o actualizar un producto
    Producto guardar(Producto producto);

    // Eliminar producto por ID
    void eliminarPorId(Integer id);

    // Buscar productos por categoría
    List<Producto> buscarPorCategoriaId(Integer idCategoria);
}
package com.restaurante.ceviche.model.service.serviceProducto.categoria;

import com.restaurante.ceviche.model.entidad.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    // Obtener todas las categorías
    List<Categoria> listarTodas();

    // Buscar categoría por ID
    Optional<Categoria> buscarPorId(Integer id);

    // Buscar categoría por nombre
    Optional<Categoria> buscarPorNombre(String nombre);

    // Guardar una categoría nueva o actualizar una existente
    Categoria guardar(Categoria categoria);

    // Eliminar una categoría por ID
    void eliminarPorId(Integer id);
}
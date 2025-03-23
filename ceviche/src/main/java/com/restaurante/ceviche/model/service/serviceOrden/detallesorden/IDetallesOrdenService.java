package com.restaurante.ceviche.model.service.serviceOrden.detallesorden;

import com.restaurante.ceviche.model.entidad.DetallesOrden;
import java.util.List;
import java.util.Optional;

public interface IDetallesOrdenService {

    // Obtener todos los detalles de orden
    List<DetallesOrden> findAll();

    // Encontrar un detalle de orden por su ID
    Optional<DetallesOrden> findById(Integer id);

    // Guardar un detalle de orden
    DetallesOrden save(DetallesOrden detallesOrden);

    // Eliminar un detalle de orden por su ID
    void deleteById(Integer id);

    // Método adicional: buscar por ID de orden
    List<DetallesOrden> findByOrdenId(Integer ordenId);
}

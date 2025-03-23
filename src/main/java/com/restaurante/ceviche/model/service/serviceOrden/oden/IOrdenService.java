package com.restaurante.ceviche.model.service.serviceOrden.oden;


import com.restaurante.ceviche.model.entidad.Orden;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {

    // Listar todas las órdenes
    List<Orden> findAll();

    // Buscar una orden por su ID
    Optional<Orden> findById(Integer id);

    // Guardar o actualizar una orden
    Orden save(Orden orden);

    // Eliminar una orden por su ID
    void deleteById(Integer id);

    // Buscar órdenes por cliente
    List<Orden> findByClienteId(Integer clienteId);

    // Calcular el total de una orden por ID
    Double calcularTotalOrden(Integer ordenId);
}
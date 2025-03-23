package com.restaurante.ceviche.model.service.serviceOrden.cliente;

import com.restaurante.ceviche.model.entidad.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {

    // Método para obtener todos los clientes
    List<Cliente> findAll();

    // Método para encontrar un cliente por su ID
    Optional<Cliente> findById(Integer id);

    // Método para guardar un cliente
    Cliente save(Cliente cliente);

    // Método para eliminar un cliente por su ID
    void deleteById(Integer id);

    // Método adicional: buscar un cliente por nombre (opcional)
    Optional<Cliente> findByNombre(String nombre);
}

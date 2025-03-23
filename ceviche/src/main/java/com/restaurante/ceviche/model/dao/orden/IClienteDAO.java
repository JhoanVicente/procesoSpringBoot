package com.restaurante.ceviche.model.dao.orden;

import com.restaurante.ceviche.model.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Integer> {
    // Puedes agregar métodos personalizados si necesitas filtrar o buscar clientes específicos
    // Por ejemplo:
    // Optional<Cliente> findByNombre(String nombre);
}

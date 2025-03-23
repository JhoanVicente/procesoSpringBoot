package com.restaurante.ceviche.model.dao.orden;

import com.restaurante.ceviche.model.entidad.Orden;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenDAO extends JpaRepository<Orden, Integer> {
    // Métodos adicionales personalizados para trabajar con órdenes
    // Por ejemplo:
    // List<Orden> findByIdCliente(Integer idCliente);
    // List<Orden> findByNumeroMesa(Integer numeroMesa);
    List<Orden> findByCliente_IdCliente(Integer idCliente);

}

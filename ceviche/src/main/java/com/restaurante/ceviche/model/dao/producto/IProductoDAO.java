package com.restaurante.ceviche.model.dao.producto;

import com.restaurante.ceviche.model.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDAO extends JpaRepository<Producto, Integer> {
    // Este método busca todos los productos
    List<Producto> findAll(); // Esto es automático con JpaRepository

    // Buscar productos por categoría (si es necesario)
    List<Producto> findByCategoria_IdCategoria(Integer idCategoria);
}

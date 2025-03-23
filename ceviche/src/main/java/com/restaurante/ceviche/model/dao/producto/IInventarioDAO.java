package com.restaurante.ceviche.model.dao.producto;

import com.restaurante.ceviche.model.entidad.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario, Integer> {
    // Buscar inventarios por el ID del producto
    List<Inventario> findByProductoIdProducto(Integer idProducto);
}

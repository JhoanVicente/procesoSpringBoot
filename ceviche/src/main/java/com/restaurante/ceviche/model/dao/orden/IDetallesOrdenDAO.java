package com.restaurante.ceviche.model.dao.orden;

import com.restaurante.ceviche.model.entidad.DetallesOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDetallesOrdenDAO extends JpaRepository<DetallesOrden, Integer> {

    // Consulta personalizada para buscar detalles por ID de orden
    List<DetallesOrden> findByOrden_IdOrden(Integer idOrden);
}

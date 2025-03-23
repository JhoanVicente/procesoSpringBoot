package com.restaurante.ceviche.model.dao.producto;

import com.restaurante.ceviche.model.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDAO extends JpaRepository<Categoria, Integer> {
    // Puedes agregar métodos personalizados si los necesitas
    // Ejemplo: Buscar categoría por nombre
    Categoria findByNombre(String nombre);
}
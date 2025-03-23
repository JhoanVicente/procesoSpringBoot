package com.restaurante.ceviche.model.dao.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.ceviche.model.entidad.Rol;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Integer> {
    // Métodos personalizados para el CRUD si son necesarios
    // Ejemplo: Buscar un rol por su tipo
    Rol findByTipoRol(String tipoRol);
}
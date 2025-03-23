package com.restaurante.ceviche.model.dao.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.ceviche.model.entidad.Trabajador;

@Repository
public interface ITrabajadorDAO extends JpaRepository<Trabajador, Integer> {
    Trabajador findByDni(String dni);
}

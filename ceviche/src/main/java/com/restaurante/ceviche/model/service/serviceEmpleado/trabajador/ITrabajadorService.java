package com.restaurante.ceviche.model.service.serviceEmpleado.trabajador;

import java.util.List;

import com.restaurante.ceviche.model.entidad.Trabajador;

public interface ITrabajadorService {
    Trabajador getTrabajadorByDni(String dni);
    List<Trabajador> getAllTrabajadores();
    Trabajador saveTrabajador(Trabajador trabajador);
    Trabajador getTrabajadorById(Integer id);
    void deleteTrabajador(Integer id);
}

package com.restaurante.ceviche.model.service.serviceEmpleado.rol;


import java.util.List;

import com.restaurante.ceviche.model.entidad.Rol;

public interface IRolService {
    Rol saveRol(Rol rol); // Crear o actualizar un rol
    List<Rol> getAllRoles(); // Listar todos los roles
    Rol getRolById(Integer id); // Buscar un rol por ID
    void deleteRol(Integer id); // Eliminar un rol
    Rol getRolByTipo(String tipoRol); // Buscar un rol por tipo
}
package com.restaurante.ceviche.model.service.serviceEmpleado.usuario;

import java.util.List;

import com.restaurante.ceviche.model.entidad.Usuario;

public interface IUsuarioService {
    Usuario saveUsuario(Usuario usuario); // Crear o actualizar un usuario
    List<Usuario> getAllUsuarios(); // Listar todos los usuarios
    Usuario getUsuarioById(Integer id); // Buscar un usuario por ID
    Usuario getUsuarioByCorreo(String correo); // Buscar un usuario por correo
    void deleteUsuario(Integer id); // Eliminar un usuario
}
package com.restaurante.ceviche.model.dao.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.ceviche.model.entidad.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    // Métodos personalizados para el CRUD
    // Ejemplo: Buscar un usuario por su correo
    Usuario findByCorreo(String correo);

    // Buscar un usuario por correo y contraseña (login)
    Usuario findByCorreoAndPassword(String correo, String password);
}
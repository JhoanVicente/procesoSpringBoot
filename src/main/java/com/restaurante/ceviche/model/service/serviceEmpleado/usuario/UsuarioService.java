package com.restaurante.ceviche.model.service.serviceEmpleado.usuario;

import com.restaurante.ceviche.model.dao.personal.IUsuarioDAO;
import com.restaurante.ceviche.model.entidad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public Usuario getUsuarioByCorreo(String correo) {
        return usuarioDAO.findByCorreo(correo);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioDAO.deleteById(id);
    }
}
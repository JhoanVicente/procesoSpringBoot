package com.restaurante.ceviche.model.service.serviceEmpleado.rol;

import com.restaurante.ceviche.model.dao.personal.IRolDAO;
import com.restaurante.ceviche.model.entidad.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRolDAO rolDAO;

    @Override
    public Rol saveRol(Rol rol) {
        return rolDAO.save(rol);
    }

    @Override
    public List<Rol> getAllRoles() {
        return rolDAO.findAll();
    }

    @Override
    public Rol getRolById(Integer id) {
        return rolDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteRol(Integer id) {
        rolDAO.deleteById(id);
    }

    @Override
    public Rol getRolByTipo(String tipoRol) {
        return rolDAO.findByTipoRol(tipoRol);
    }
}
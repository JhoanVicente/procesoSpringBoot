package com.restaurante.ceviche.model.service.serviceEmpleado.trabajador;

import com.restaurante.ceviche.model.dao.personal.ITrabajadorDAO;
import com.restaurante.ceviche.model.entidad.Trabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService implements ITrabajadorService {

    @Autowired
    private ITrabajadorDAO trabajadorDAO;

    @Override
    public Trabajador saveTrabajador(Trabajador trabajador) {
        return trabajadorDAO.save(trabajador);
    }

    @Override
    public List<Trabajador> getAllTrabajadores() {
        return trabajadorDAO.findAll();
    }

    @Override
    public Trabajador getTrabajadorById(Integer id) {
        return trabajadorDAO.findById(id).orElse(null);
    }

    @Override
    public Trabajador getTrabajadorByDni(String dni) {
        return trabajadorDAO.findByDni(dni);
    }

    @Override
    public void deleteTrabajador(Integer id) {
        trabajadorDAO.deleteById(id);
    }
}
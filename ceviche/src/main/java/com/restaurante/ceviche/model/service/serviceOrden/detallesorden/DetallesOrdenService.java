package com.restaurante.ceviche.model.service.serviceOrden.detallesorden;

import com.restaurante.ceviche.model.dao.orden.IDetallesOrdenDAO;
import com.restaurante.ceviche.model.entidad.DetallesOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesOrdenService implements IDetallesOrdenService {

    @Autowired
    private IDetallesOrdenDAO detallesOrdenDAO;

    @Override
    public List<DetallesOrden> findAll() {
        return detallesOrdenDAO.findAll();
    }

    @Override
    public Optional<DetallesOrden> findById(Integer id) {
        return detallesOrdenDAO.findById(id);
    }

    @Override
    public DetallesOrden save(DetallesOrden detallesOrden) {
        return detallesOrdenDAO.save(detallesOrden);
    }

    @Override
    public void deleteById(Integer id) {
        detallesOrdenDAO.deleteById(id);
    }

    @Override
    public List<DetallesOrden> findByOrdenId(Integer ordenId) {
        return detallesOrdenDAO.findByOrden_IdOrden(ordenId);
    }
}

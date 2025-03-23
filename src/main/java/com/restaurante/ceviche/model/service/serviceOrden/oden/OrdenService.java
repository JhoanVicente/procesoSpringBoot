package com.restaurante.ceviche.model.service.serviceOrden.oden;
import com.restaurante.ceviche.model.dao.orden.IOrdenDAO;
import com.restaurante.ceviche.model.entidad.Orden;
import com.restaurante.ceviche.model.entidad.DetallesOrden;
import com.restaurante.ceviche.model.dao.orden.IDetallesOrdenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IOrdenService {

    @Autowired
    private IOrdenDAO ordenDAO;

    @Autowired
    private IDetallesOrdenDAO detallesOrdenDAO;

    @Override
    public List<Orden> findAll() {
        return ordenDAO.findAll();
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenDAO.findById(id);
    }

    @Override
    public Orden save(Orden orden) {
        return ordenDAO.save(orden);
    }

    @Override
    public void deleteById(Integer id) {
        ordenDAO.deleteById(id);
    }

    @Override
    public List<Orden> findByClienteId(Integer clienteId) {
        return ordenDAO.findByCliente_IdCliente(clienteId);
    }

    @Override
    public Double calcularTotalOrden(Integer ordenId) {
        List<DetallesOrden> detalles = detallesOrdenDAO.findByOrden_IdOrden(ordenId);

        return detalles.stream()
                .mapToDouble(detalle -> detalle.getCantidad() * detalle.getPrecioUnitario().doubleValue())
                .sum();
    }
}
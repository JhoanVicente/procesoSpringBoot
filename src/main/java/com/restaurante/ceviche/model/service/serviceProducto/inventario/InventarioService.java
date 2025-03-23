package com.restaurante.ceviche.model.service.serviceProducto.inventario;

import com.restaurante.ceviche.model.dao.producto.IInventarioDAO;
import com.restaurante.ceviche.model.entidad.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private IInventarioDAO inventarioDAO;

    @Override
    public List<Inventario> listarTodo() {
        return inventarioDAO.findAll();
    }

    @Override
    public Optional<Inventario> buscarPorId(Integer id) {
        return inventarioDAO.findById(id);
    }

    @Override
    public List<Inventario> buscarPorProductoId(Integer idProducto) {
        return inventarioDAO.findByProductoIdProducto(idProducto);
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return inventarioDAO.save(inventario);
    }

    @Override
    public void eliminarPorId(Integer id) {
        inventarioDAO.deleteById(id);
    }
}
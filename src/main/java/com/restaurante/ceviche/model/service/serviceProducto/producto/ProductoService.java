package com.restaurante.ceviche.model.service.serviceProducto.producto;

import com.restaurante.ceviche.model.dao.producto.IProductoDAO;
import com.restaurante.ceviche.model.entidad.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public List<Producto> listarTodo() {
        return productoDAO.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        return productoDAO.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoDAO.deleteById(id);
    }

    @Override
    public List<Producto> buscarPorCategoriaId(Integer idCategoria) {
        return productoDAO.findByCategoria_IdCategoria(idCategoria);
    }
}

package com.restaurante.ceviche.model.service.serviceProducto.categoria;

import com.restaurante.ceviche.model.dao.producto.ICategoriaDAO;
import com.restaurante.ceviche.model.entidad.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> listarTodas() {
        return categoriaDAO.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaDAO.findById(id);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        return Optional.ofNullable(categoriaDAO.findByNombre(nombre));
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaDAO.save(categoria);
    }

    @Override
    public void eliminarPorId(Integer id) {
        categoriaDAO.deleteById(id);
    }
}
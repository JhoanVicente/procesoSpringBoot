package com.restaurante.ceviche.model.service.serviceOrden.cliente;

import com.restaurante.ceviche.model.dao.orden.IClienteDAO;
import com.restaurante.ceviche.model.entidad.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteDAO.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        clienteDAO.deleteById(id);
    }

    @Override
    public Optional<Cliente> findByNombre(String nombre) {
        // Método adicional: Implementar lógica personalizada si es necesario
        return clienteDAO.findAll()
                         .stream()
                         .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                         .findFirst();
    }
}

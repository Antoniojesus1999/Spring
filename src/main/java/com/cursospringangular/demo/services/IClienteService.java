package com.cursospringangular.demo.services;

import com.cursospringangular.demo.entity.Cliente;
import java.util.List;

public interface IClienteService {
    public List<Cliente> findAll();
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void deleteById(Long id);
}

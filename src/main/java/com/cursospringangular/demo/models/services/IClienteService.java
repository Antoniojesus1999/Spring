package com.cursospringangular.demo.models.services;

import com.cursospringangular.demo.models.entity.Cliente;
import com.cursospringangular.demo.models.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();
    Page<Cliente> findAll(Pageable pageable);
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    List<Region> findAllRegiones();
}

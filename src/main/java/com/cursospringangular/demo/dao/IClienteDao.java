package com.cursospringangular.demo.dao;

import com.cursospringangular.demo.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
public interface IClienteDao extends CrudRepository<Cliente, Long> {
}

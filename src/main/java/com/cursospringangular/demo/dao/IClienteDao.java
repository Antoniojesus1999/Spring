package com.cursospringangular.demo.dao;

import com.cursospringangular.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IClienteDao extends JpaRepository<Cliente, Long> {
}

package com.cursospringangular.demo.models.dao;

import com.cursospringangular.demo.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
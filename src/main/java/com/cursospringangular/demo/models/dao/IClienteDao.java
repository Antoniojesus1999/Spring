package com.cursospringangular.demo.models.dao;

import com.cursospringangular.demo.models.entity.Cliente;
import com.cursospringangular.demo.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

    @Query("from Region")
    public List<Region> findAllRegiones();
}

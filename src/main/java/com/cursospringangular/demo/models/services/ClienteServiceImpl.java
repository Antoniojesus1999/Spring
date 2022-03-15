package com.cursospringangular.demo.models.services;

import com.cursospringangular.demo.models.dao.IClienteDao;
import com.cursospringangular.demo.models.entity.Cliente;
import com.cursospringangular.demo.models.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    IClienteDao iClienteDao;
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return iClienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return iClienteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return iClienteDao.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return iClienteDao.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        iClienteDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones(){
        return iClienteDao.findAllRegiones();
    }


}

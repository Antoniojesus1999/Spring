package com.cursospringangular.demo.services;

import com.cursospringangular.demo.dao.IClienteDao;
import com.cursospringangular.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
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
        return (List<Cliente>)iClienteDao.findAll();
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


}

package com.cursospringangular.demo.models.services;

import com.cursospringangular.demo.models.dao.IClienteDao;
import com.cursospringangular.demo.models.dao.IFacturaDao;
import com.cursospringangular.demo.models.dao.IProductoDao;
import com.cursospringangular.demo.models.entity.Cliente;
import com.cursospringangular.demo.models.entity.Factura;
import com.cursospringangular.demo.models.entity.Producto;
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
    @Autowired
    IFacturaDao iFacturaDao;
    @Autowired
    IProductoDao iProductoDao;
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

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return iFacturaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return iFacturaDao.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        iFacturaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findProductoByNombre(String term) {
        return iProductoDao.findByNombreContains(term);
    }


}

package com.cursospringangular.demo.controller;

import com.cursospringangular.demo.models.entity.Factura;
import com.cursospringangular.demo.models.entity.Producto;
import com.cursospringangular.demo.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ={"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
    @Autowired
    private IClienteService clienteService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }


    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteService.deleteFacturaById(id);
    }

    @GetMapping("/facturas/filtrar-productos/{term}")
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteService.findProductoByNombre(term);
    }

    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crear(@RequestBody Factura factura){
        return clienteService.saveFactura(factura);

    }
}

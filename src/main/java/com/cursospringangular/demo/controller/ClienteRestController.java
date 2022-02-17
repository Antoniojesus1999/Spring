package com.cursospringangular.demo.controller;

import com.cursospringangular.demo.entity.Cliente;
import com.cursospringangular.demo.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/api")

@CrossOrigin(origins = {"http://localhost:4200"})
public class ClienteRestController {

    @Autowired
    private ClienteServiceImpl clienteService;
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("clientes")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = null;
        try{
            cliente =clienteService.findById(id);
        }catch(NoSuchElementException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(cliente == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteService.deleteById(id);
    }

    @PutMapping("/clientes")
    public Cliente update(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

}

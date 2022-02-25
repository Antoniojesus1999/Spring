package com.cursospringangular.demo.controller;

import com.cursospringangular.demo.entity.Cliente;
import com.cursospringangular.demo.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        Cliente cliente;
        try{
            cliente =clienteService.findById(id);
        }catch(NoSuchElementException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("mensaje2", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(cliente == null){
            response.put("mensaje","El valor de cliente al recuperarlo de la bd es null");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Cliente cliente){
        Cliente newCliente;
        Map<String, Object> response = new HashMap<>();
        try{
            newCliente = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al insertar en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido creado con éxito!");
        response.put("cliente",newCliente);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteService.deleteById(id);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente,@PathVariable Long id){
        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdated;

        Map<String, Object> response = new HashMap<>();
        if(clienteActual == null){
            response.put("mensaje","Error no se puedo editar el cliente ".concat(id.toString()).concat(" no existe en bd") );
            response.put("cliente",clienteActual);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        try {
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());
            clienteUpdated = clienteService.save(clienteActual);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", clienteUpdated);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}

package com.cursospringangular.demo.controller;

import com.cursospringangular.demo.entity.Cliente;
import com.cursospringangular.demo.services.ClienteServiceImpl;
import com.cursospringangular.demo.services.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

@CrossOrigin(origins = {"http://localhost:4200"})
public class ClienteRestController {

    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private IUploadFileService uploadFileService;
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("clientes")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page ){
        Pageable pageable = PageRequest.of(page ,4);
        return clienteService.findAll(pageable);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente;
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

    /**
     *
     * @param cliente .
     * @param result .
     * @return
     */
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente newCliente;
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String>errors = result.getFieldErrors().stream().map(err ->"El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.findById(id);
            String nombreArchivoAnterior = cliente.getFoto();
            uploadFileService.eliminar(nombreArchivoAnterior);
            clienteService.deleteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido eliminado con exito");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Long id){
        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdated;

        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String>errors = result.getFieldErrors().stream().map(err ->"El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
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

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id ){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteService.findById(id);

        if(!archivo.isEmpty()){

            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                e.printStackTrace();
            }
            String nombreArchivoAnterior = cliente.getFoto();
            uploadFileService.eliminar(nombreArchivoAnterior);

            cliente.setFoto(nombreArchivo);
            clienteService.save(cliente);
            response.put("cliente",cliente);
            response.put("mensaje","Has subido correctamente la foto "+ nombreArchivo);

        }else{
            response.put("error","NO se ha encontrado ningún usuario por ese id");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        try {
            recurso = uploadFileService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");

        return new ResponseEntity<>(recurso,cabecera,HttpStatus.OK);

    }

}

package com.cursospringangular.demo.models.services;

import com.cursospringangular.demo.models.entity.Usuario;


public interface IUsuarioService {

	Usuario findByUsername(String username);
}

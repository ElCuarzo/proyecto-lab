package com.elcuarzo.mvc.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elcuarzo.mvc.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
	List<Usuario> findAll();
	Usuario getByCorreo(String correo);
	Usuario getById(Long id);
}

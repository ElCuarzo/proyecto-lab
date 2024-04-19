package com.elcuarzo.mvc.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elcuarzo.mvc.modelo.Canciones;

public interface CancionesRepositorio extends CrudRepository<Canciones, Long>{
	List<Canciones> findAll();
	Canciones getByNombre(String nombre);
	Canciones getById(Long id);
}

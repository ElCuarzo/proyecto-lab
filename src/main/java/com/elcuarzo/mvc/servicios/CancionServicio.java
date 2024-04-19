package com.elcuarzo.mvc.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elcuarzo.mvc.modelo.Canciones;
import com.elcuarzo.mvc.repositorios.CancionesRepositorio;

@Service
public class CancionServicio {
	
	private final CancionesRepositorio cancionRepositorio;
	
	public CancionServicio(CancionesRepositorio cancionRepositorio) {
		this.cancionRepositorio = cancionRepositorio;
	}
	
	//Obtener todas las Canciones
	public List<Canciones> obtenerTodasLasCanciones(){
		return cancionRepositorio.findAll();
	}
	
	//Crear nueva cancion
	public Canciones crearCancion(Canciones nuevaCancion) {
		return this.cancionRepositorio.save(nuevaCancion);
	}
	
	//Obtener cancion por nombre
	public Canciones obtenerCancionPorNombre(String nombreCancion) {
        return cancionRepositorio.getByNombre(nombreCancion);
    }
	
	//Obtener cancion por id
	public Canciones obtenerCancionPorId(Long id) {
		return cancionRepositorio.getById(id);
	}
}

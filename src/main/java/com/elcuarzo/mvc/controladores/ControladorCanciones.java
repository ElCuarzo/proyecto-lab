package com.elcuarzo.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elcuarzo.mvc.modelo.Canciones;
import com.elcuarzo.mvc.modelo.Usuario;
import com.elcuarzo.mvc.servicios.CancionServicio;
import com.elcuarzo.mvc.servicios.LoginServicio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {
	
	private final CancionServicio cancionServicio;
	private final LoginServicio loginServicio;
	
	public ControladorCanciones(CancionServicio cancionServicio, LoginServicio loginServicio) {
		this.cancionServicio = cancionServicio;
		this.loginServicio = loginServicio;
	}
	
	@RequestMapping("/home")
	public String desplegarCanciones(Model modelo, HttpSession sesion) {
		List<Canciones> cancion = this.cancionServicio.obtenerTodasLasCanciones();
		modelo.addAttribute("cancion", cancion);
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if(idUsuario == null) {
			return "redirect:/";
		}
		Map<Long, Integer> colaboradoresPorCancion = new HashMap<>();
	    for (Canciones canciones : cancion) {
	        int numeroColaboradores = canciones.getUsuarios().size();
	        colaboradoresPorCancion.put(canciones.getId(), numeroColaboradores);
	    }
	    modelo.addAttribute("colaboradoresPorCancion", colaboradoresPorCancion);
		return "Home.jsp";
	}
	
	@RequestMapping("/nueva/cancion")
	public String despliegaFormularioCancion(@ModelAttribute("canciones") Canciones canciones,
			HttpSession sesion) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if(idUsuario == null) {
			return "redirect:/";
		}
		return "FormularioCancion.jsp";
	}
	
	@RequestMapping(value="/nueva/cancion", method = RequestMethod.POST)
	public String agregarCancion(@Valid @ModelAttribute("canciones") Canciones cancionNueva,
								BindingResult resultado,
								HttpSession sesion) {
		
		if(resultado.hasErrors()) {
			return "FormularioCancion.jsp";
		}
		Long idUsuario= (Long) sesion.getAttribute("idUsuario");
		if(idUsuario == null) {
			return "redirect:/";
		}
		Usuario usuarioActual = this.loginServicio.selectPorId(idUsuario);
		cancionNueva.setCreador(usuarioActual.getNombre());
		cancionNueva.getUsuarios().add(usuarioActual);
		
		Canciones cancionExistente = this.cancionServicio.obtenerCancionPorNombre(cancionNueva.getNombre());
	    if (cancionExistente != null) {
	        resultado.rejectValue("nombre", "Duplicate", "Ya existe una canción con este nombre.");
	        return "FormularioCancion.jsp";
	    }
		this.cancionServicio.crearCancion(cancionNueva);
		return "redirect:/home";
	}
	
	@RequestMapping("/cancion/{id}")
	public String mostrarCancion(@PathVariable("id") Long id, Model model, HttpSession sesion) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
	    if(idUsuario == null) {
	        return "redirect:/";
	    }
		Canciones cancionActual = cancionServicio.obtenerCancionPorId(id);
		model.addAttribute("cancionActual", cancionActual);
		return "mostrarCanciones.jsp";
	}
	
	@RequestMapping("/canciones/{id}/editar")
	public String editarCancion(@PathVariable("id") Long id ,Model model, HttpSession sesion) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
	    if(idUsuario == null) {
	        return "redirect:/";
	    }
		Canciones cancionActual = cancionServicio.obtenerCancionPorId(id);
		model.addAttribute("cancionActual", cancionActual);
		return "editarCancion.jsp";
	}
	
	@RequestMapping(value="/procesar/editar/{id}", method = RequestMethod.POST)
	public String procesarEditar(@Valid @ModelAttribute("cancionActual") Canciones cancionActual,
            					 BindingResult resultado,
								 @PathVariable("id") Long id,
	                             HttpSession sesion,
	                             @RequestParam("nuevaLetra") String nuevaLetra) {
		
		System.out.println("hola");
		if(resultado.hasErrors()) {
			System.out.println("error");
	        return "editarCancion.jsp";
	    }

	    Long idUsuario = (Long) sesion.getAttribute("idUsuario");
	    if(idUsuario == null) {
	        return "redirect:/";
	    }
	    Usuario usuarioActual = this.loginServicio.selectPorId(idUsuario);
	    Canciones cancionExistente = this.cancionServicio.obtenerCancionPorId(id);
	    if (cancionExistente == null) {
	        return "redirect:/home";
	    }
	    String letraActual = cancionExistente.getLetra();
	    cancionExistente.setLetra(letraActual + "\n\n" + nuevaLetra);
	    cancionExistente.setNombre(cancionActual.getNombre());
	    cancionExistente.setGenero(cancionActual.getGenero());
	    if (!cancionExistente.getUsuarios().contains(usuarioActual)) {
	        cancionExistente.getUsuarios().add(usuarioActual);
	    }
	    this.cancionServicio.crearCancion(cancionExistente);
	    return "redirect:/home";
	}


}

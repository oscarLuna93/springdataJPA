package com.oscar.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oscar.springboot.app.models.entity.Cliente;
import com.oscar.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clienteService.findAll());
		
		return "listar";
	}
	
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario de Cliente");
		model.put("cliente", cliente);
		
		return "form";
	}
	
	@PostMapping(value = "/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		
		String mensajeFlash = (cliente.getId() == null) ? "cliente creado con exito" : "cliente editado con exito";
		
		clienteService.save(cliente);
		//para eliminar cliente de la sesion
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe en la BD");
			}
		} else {
			flash.addFlashAttribute("error", "El cliente no puede ser cero");
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar Cliente");
		
		return "form";
	}
	
	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "cliente eliminado con exito");
		}

		return "redirect:/listar";
	}
}

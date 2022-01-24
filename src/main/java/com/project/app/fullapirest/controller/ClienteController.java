package com.project.app.fullapirest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.fullapirest.models.entity.Cliente;
import com.project.app.fullapirest.models.service.IClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app")
public class ClienteController {

	@Autowired
	private IClienteService iClienteService;

	@GetMapping("/clientes")
	public List<Cliente> consultaClientes() {
		return iClienteService.findAll();

	}

	/*
	 * @GetMapping("/clientes/page/{page}") public Page<Cliente>
	 * consultaClientes(PathVariable Integer page) { return
	 * iClienteService.findAll();
	 * 
	 * }
	 */

	
	//ResponseEntity<?> se puso el ? para indicar qe puede ser cualquier tipo de dato (Generico)
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> consultaById(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = iClienteService.findById(id);
		}catch(DataAccessException e){
			response.put("mensaje","Error al consultar en la bd");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		
		if(cliente==null) {
			
			response.put("mensaje","EL cliente ID:".concat(id.toString().concat(" No existe en la BD")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<Cliente>(cliente, HttpStatus.OK); 
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> guardarCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
		// RequestBody sirve para poder mandar la informacion en formato Json y asi poderse buscar
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField() +"' "+err.getDefaultMessage()).collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			clienteNew = iClienteService.save(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje","Error ak Insertar el cliente en la bd");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje","El cliente ha sido creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity <Map<String, Object>>(response, HttpStatus.CREATED);

		
		
		
	}

	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> actualizarCliente(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = iClienteService.findById(id);
		Cliente clienteeAct = null;

		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField() +"' "+err.getDefaultMessage()).collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		

		if (clienteActual == null) {

			response.put("mensaje",
					"Error: No se pudo editar el cliente ID:".concat(id.toString().concat(" No existe en la BD")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setFecha(cliente.getFecha());

			clienteeAct = iClienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error ak actualizar el cliente en la bd");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje","El cliente ha sido creado con exito");
		response.put("cliente", clienteeAct);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> borrarCliente(@PathVariable Long id) {

		
		Map<String, Object> response = new HashMap<>();
		try {
				iClienteService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error ak eliminar el cliente en la bd");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente elliminado se elimino con extio");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	
	}

	
	
	

}

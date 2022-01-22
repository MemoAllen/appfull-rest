package com.project.app.fullapirest.models.service;

import java.util.List;

import com.project.app.fullapirest.models.entity.Cliente;


public interface IClienteService {

	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);

}
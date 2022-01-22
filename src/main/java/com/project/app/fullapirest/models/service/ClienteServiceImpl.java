package com.project.app.fullapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.app.fullapirest.models.dao.IClienteRepository;
import com.project.app.fullapirest.models.entity.Cliente;




@Service
public class ClienteServiceImpl implements IClienteService {

	 @Autowired
	 private IClienteRepository iClienteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		
		 	
		return (List<Cliente>) iClienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return iClienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return iClienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
		iClienteRepository.deleteById(id);
	}


}

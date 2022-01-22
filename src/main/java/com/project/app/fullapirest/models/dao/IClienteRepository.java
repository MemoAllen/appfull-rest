package com.project.app.fullapirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.app.fullapirest.models.entity.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Long>{


	
	
}

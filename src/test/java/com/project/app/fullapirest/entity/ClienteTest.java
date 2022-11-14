package com.project.app.fullapirest.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.app.fullapirest.models.entity.Cliente;


@ExtendWith(MockitoExtension.class)
public class ClienteTest {

	
	private static final Logger logger = LoggerFactory.getLogger(Cliente.class);
	
	
	
	@InjectMocks
	private Cliente cliente;
	
	@Test
	void clienteTest() {
		logger.info("Inicio de Test clienteTest");
		
		cliente = new Cliente();
		
		cliente.setId(1L);
		cliente.setNombre("Guillermo");
		cliente.setApellido("Balderas");
		cliente.setEmail("memodj345.jgbz@gmail.com");
		Date  fecha = new Date();
		cliente.setFecha(fecha);
		
		
		cliente.getId();
		cliente.getNombre();
		cliente.getApellido();
		cliente.getEmail();
		cliente.getFecha();
		
		logger.info(String.format("Cliente: "+ cliente.getNombre()+" "+cliente.getApellido()));
		
		
		cliente.equals(cliente);
		cliente.equals(new Cliente());
		
		cliente.hashCode();
		cliente.toString();	
		
		logger.info("Termina Test clienteTest");
		assertNotNull(cliente);
	}
}

package com.nivelamento.minhasfinancas.model.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nivelamento.minhasfinancas.model.Repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuariosService service;
	@Autowired
	UsuarioRepository repo;
	@Test
	public void deveValidarEmail() {
		//cenario
		
		repo.deleteAll();
		
		//acao
		service.validarEmail("email@email.com");
	}
}
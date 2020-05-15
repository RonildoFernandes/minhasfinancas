package com.nivelamento.minhasfinancas.model.Service;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Excepition.RegraNegocioExcepition;
import com.nivelamento.minhasfinancas.model.Repository.UsuarioRepository;
import com.nivelamento.minhasfinancas.model.Service.Impl.UsuarioServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuariosService service;
	@Autowired
	UsuarioRepository repo;
	
	@Before
	public void setUp() {
		repo = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImp(repo);
	}
	
	@Test
	public void deveValidarEmail() {
		//cenario
		
		Mockito.when(repo.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("email@email.com");
	}
	
	@Test
	public void deveLancarErroQuandoexistirEmailCadastrado() 
	{
		
		Assertions.assertThrows(RegraNegocioExcepition.class, () -> 
		{
			//cenario
			Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
			repo.save(usuario);
		
			//açao
			service.validarEmail("email@email.com");
		});
		
   
 }
}

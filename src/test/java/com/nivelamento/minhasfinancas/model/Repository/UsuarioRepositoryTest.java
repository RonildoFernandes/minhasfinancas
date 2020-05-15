package com.nivelamento.minhasfinancas.model.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.nivelamento.minhasfinancas.model.Entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	@Autowired
	UsuarioRepository repo;
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
		repo.save(usuario);
		//ação
		
		
		boolean result = repo.existsByEmail("usuario@email.com");
		
		
		//verificação
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public  void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenário
		repo.deleteAll();
		
		//ação
		boolean result = repo.existsByEmail("usuario@email.com");
		
		//verificação;
		Assertions.assertThat(result).isFalse();
	}
}
